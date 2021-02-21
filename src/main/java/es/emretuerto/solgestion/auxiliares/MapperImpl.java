package es.emretuerto.solgestion.auxiliares;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import es.emretuerto.solgestion.excepciones.MappingException;
import es.emretuerto.solgestion.auxiliares.anotaciones.MappingDateFormat;
import es.emretuerto.solgestion.auxiliares.anotaciones.MappingDeep;
import es.emretuerto.solgestion.auxiliares.anotaciones.MappingName;
import es.emretuerto.solgestion.auxiliares.anotaciones.MappingNumberFormat;


/**
 * Clase que mapea un objeto de una clase en otro de otra teniendo en cuenta que
 * los campos en ambas clases deben llamarse igual, aunque el tipo difiera
 * 
 * @author atraspuesto
 *
 */
public final class MapperImpl implements MapperInterface {

    private NumberFormat nf;


    protected MapperImpl(Locale locale) {

	nf = NumberFormat.getInstance(locale);
    }


    @Override
    public <R, P> R map(Class<R> dstClass, P src) throws MappingException {

	return map(dstClass, src, null);
    }


    /**
     * Mapea la instancia src en una de la clase definida por dstClass
     * 
     * @param dstClass
     * @param src
     * @param depth
     *            depth[0] = profundidad actual, depth[1] profundidad maxima
     * @return
     */
    @SuppressWarnings("deprecation")
	private <R, P> R map(Class<R> dstClass, P src, int[] depth) {

	R result = null;
	if(src == null) {
	    return result;
	}
	try {
	    result = dstClass.newInstance();
	    Field[] srcFields = src.getClass().getDeclaredFields();
	    // Cargo los campos destino y establezco las profundidades
	    Map<String, Field> fieldInfoMap = composeFieldInfoMap(dstClass);
	    for (Field field : srcFields) {
		// Recorro todos los campos origen y los mapeo si existen en el
		// destino
		field.setAccessible(true);
		String name = getFieldName(field);
		Field currField = fieldInfoMap.get(name);

		if(currField != null) {
		    // obtengo la profundidad desde la que se parte y a la que
		    // debe llegarse
		    int[] currDepth = setDepth(currField, depth);
		    mapField(src, result, field, currField, currDepth);
		}

	    }
	}
	catch (Exception ex) {
	    throw new MappingException("mapping failed", ex);
	}
	return result;
    }


    /**
     * Devuelve el nombre que identifica al atributo de cara al mapeo
     * 
     * @param field
     * @return
     */
    private String getFieldName(Field field) {

	String name = field.getName();
	if(field.isAnnotationPresent(MappingName.class)) {
	    name = field.getAnnotation(MappingName.class).value();
	}
	return name;
    }


    /**
     * Determina la profundidad actual y maxima para este campo depth[0]
     * profundidad actual depth[1] profundidad maxima depth = null si no hay
     * limite de profundidad
     * 
     * @param field
     * @return
     */
    private int[] setDepth(Field field, int[] parentDepth) {

	int[] depth = null;
	if(parentDepth != null) {
	    // prevalece la profundidad definida para el padre
	    depth = parentDepth;
	}
	else {
	    // Si el padre no define profundidad maxima uso la de este campo
	    if(field.isAnnotationPresent(MappingDeep.class)) {
		depth = new int[] { 0, field.getAnnotation(MappingDeep.class).deep() };
	    }

	}
	return depth;
    }


    /**
     * Crea un mapa que relaciona el nombre de cada atributo con el nombre al
     * que responde de cara al mapeo
     * 
     * @param dstClass
     * @return
     */
    private <R> Map<String, Field> composeFieldInfoMap(Class<R> dstClass) {

	Map<String, Field> fieldInfoMap = new HashMap<>();
	for (Field field : dstClass.getDeclaredFields()) {
	    if(!java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
		field.setAccessible(true);
		String name = getFieldName(field);
		fieldInfoMap.put(name, field);
	    }
	}
	return fieldInfoMap;
    }


    /**
     * Mapea la instancia srcValue asociada al campo srcField y se le asigna a
     * dstField
     * 
     * @param srcValue
     * @param resultInstance
     * @param srcField
     * @param dstField
     * @param depth
     * @throws IllegalAccessException
     * @throws ParseException
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    private <P, R> void mapField(P srcValue, R resultInstance, Field srcField, Field dstField, int[] depth) throws IllegalAccessException, ParseException, IllegalArgumentException, ClassNotFoundException, NoSuchFieldException, SecurityException, InstantiationException, InvocationTargetException, NoSuchMethodException {

	if(dstField.getGenericType().equals(srcField.getGenericType())) {
	    // Campo origen y destino de igual clase
	    dstField.set(resultInstance, srcField.get(srcValue));
	}
	else {
	    // es necesario transformar el objeto origen en uno del tipo de
	    // destino
	    Result result = convertFieldToDifferentClass(srcValue, srcField, dstField, depth);
	    dstField.set(resultInstance, result.value);

	}

    }


    /**
     * Se encarga de mapear la instancia origen a la clase de destino cuando son
     * de distinto tipo
     * 
     * @param srcValue
     * @param srcField
     * @param dstField
     * @param depth
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws ParseException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws SecurityException
     * @throws IllegalArgumentException
     */
    private <P> Result convertFieldToDifferentClass(P srcValue, Field srcField, Field dstField, int[] depth) throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException, ParseException, IllegalArgumentException, SecurityException, InstantiationException, InvocationTargetException, NoSuchMethodException {

	boolean tooDeep = isTooDeep(depth);
	Result result;
	if(Collection.class.isAssignableFrom(dstField.getType()) && !tooDeep) {
	    // mapeo una coleccion si no se ha alcanzado la profundidad maxima
	    depth = increaseDepth(depth);
	    result = mapCollection(dstField, srcField, srcField.get(srcValue), depth);

	}
	else if(dstField.getType().equals(String.class)) {
	    // mapeo a string
	    result = convertToString(dstField, srcField.get(srcValue));

	}
	else {
	    // intento mapear a tipo basico
	    result = convertBasic(dstField, srcField, srcField.get(srcValue));
	    if(!result.success) {
		// si no puede mapearse en tipo basico intento un mapeo complejo
		result = convertComplexObject(srcValue, srcField, dstField, depth, tooDeep);
	    }
	}
	return result;
    }


    /**
     * Determina si se ha alcanzado la maxima profundidad
     * 
     * @param depth
     * @return
     */
    private boolean isTooDeep(int[] depth) {

	boolean tooDeep = false;
	if(depth != null) {
	    // compruebo si se ha alcanzado la profundidad maxima
	    tooDeep = depth[0] >= depth[1];
	}
	return tooDeep;
    }


    /**
     * Devuelve un string a partir de srcValue formateado segun las posibles
     * anotaciones de formato con que se anote el string
     * 
     * @param dstField
     * @param srcValue
     * @return
     */
    private Result convertToString(Field dstField, Object srcValue) {

	String str = null;
	if(srcValue != null) {
	    if(srcValue instanceof Number) {
		str = numberToString(dstField, (Number) srcValue);
	    }
	    else if(srcValue instanceof Date) {
		str = dateToString(dstField, (Date) srcValue);
	    }
	    else {
		str = srcValue.toString();
	    }
	}
	return new Result(true, str);
    }


    /**
     * Devuelve un objeto result que contiene como valor un objeto del tipo
     * requerido por dstField, puede ser null. result.succes = false si srcValue
     * no puede ser transformado en un objeto de la clase requerida por dstField
     * 
     * @param dstField
     * @param srcField
     * @param srcValue
     * @return
     * @throws ParseException
     */
    public <P> Result convertBasic(Field dstField, Field srcField, P srcValue) throws ParseException {

	Object i = null;
	Class<?> dstClass = dstField.getType();
	boolean success = true;
	if(srcValue != null) {
	    if(Date.class.isAssignableFrom(dstClass)) {
		i = stringToDate(dstField, srcField, srcValue.toString());
	    }
	    else if(Number.class.isAssignableFrom(dstClass)) {
		i = stringToNumber(dstClass, srcValue.toString());
	    }
	    else if(dstClass.equals(Boolean.class)) {
		i = Boolean.valueOf(srcValue.toString());
	    }
	    else {
		success = false;
	    }
	}
	return new Result(success, i);
    }


    @SuppressWarnings("unused")
	private <P> Result convertBasic(Class<?> dstClass, P srcValue, String pattern) throws ParseException {

	Object i = null;

	boolean success = true;
	if(srcValue != null) {
	    if(Date.class.isAssignableFrom(dstClass)) {
		i = stringToDate(dstClass, srcValue.toString(), pattern);
	    }
	    else if(Number.class.isAssignableFrom(dstClass)) {
		i = stringToNumber(dstClass, srcValue.toString());
	    }
	    else if(dstClass.equals(Boolean.class)) {
		i = Boolean.valueOf(srcValue.toString());
	    }
	    else {
		success = false;
	    }
	}
	return new Result(success, i);
    }


    /**
     * Se encarga de mapear una instancia de un objeto complejo a otra de la
     * clase requerida
     * 
     * @param srcValue
     * @param srcField
     * @param dstField
     * @param depth
     * @param tooDeep
     * @return
     * @throws IllegalAccessException
     */
    private <P> Result convertComplexObject(P srcValue, Field srcField, Field dstField, int[] depth, boolean tooDeep) throws IllegalAccessException {

	Result result;
	if(tooDeep) {
	    // Si se alcanza la profundidad maxima se mapea a null
	    result = new Result(true, null);
	}
	else {
	    // Si no se ha alcanzado la profundidad maxima
	    // incremento la profundidad y llamo a map
	    depth = increaseDepth(depth);
	    result = new Result(true, map(dstField.getType(), srcField.get(srcValue), depth));
	}
	return result;
    }


    /**
     * Incrementa la profundidad en 1 si no esta vacia
     * 
     * @param depth
     * @return
     */
    private int[] increaseDepth(int[] depth) {

	if(depth != null) {
	    depth = new int[] { ++depth[0], depth[1] };
	}
	return depth;
    }


    /**
     * Se encarga del mapeo de instancias de coleciones genericas cuando las
     * clases de los objetos contenidos en las clases origen y destino son
     * distintas
     * 
     * @param dstField
     * @param srcField
     * @param srcValue
     * @param depth
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private <P> Result mapCollection(Field dstField, Field srcField, P srcValue, int[] depth) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InstantiationException, InvocationTargetException, NoSuchMethodException {

	if(srcValue == null) {
	    return new Result(true, null);
	}
	// Determino la clase que deben tener las instancias almacenadas en la
	// coleccion de destino
	Class<?> dstC = getClassOfGeneric(dstField);
	// Obtengo una instancia adecuada de la coleccion de destino
	Collection<Object> colectionDst = null;
	if(classImplementsInterfaceByName("PersistentCollection", srcValue.getClass())) {
	    if(Set.class.isAssignableFrom(srcValue.getClass())) {
		if(SortedSet.class.isAssignableFrom(srcValue.getClass())) {
		    colectionDst = new TreeSet<>();
		}
		else {
		    colectionDst = new HashSet<>();
		}

	    }
	    else if(List.class.isAssignableFrom(srcValue.getClass())) {
		colectionDst = new ArrayList<>();
		// colectionDst = (Collection<Object>)
		// srcValue.getClass().getConstructor().newInstance();
	    }
	}
	else {
	    colectionDst = (Collection<Object>) srcValue.getClass().getConstructor().newInstance();
	}
	// Relleno la coleccion de destino

	if(colectionDst != null) {
	    for (Object obj : (Collection) srcValue) {
		int[] currDepth = depth != null ? new int[] { depth[0], depth[1] } : null;
		colectionDst.add(map(dstC, obj, currDepth));
	    }
	}
	return new Result(true, colectionDst);
    }


    private boolean classImplementsInterfaceByName(String nameInterface, Class<?> cls) {

	boolean result = false;
	@SuppressWarnings("rawtypes")
	Class[] interfaces = cls.getInterfaces();
	for (int i = 0; i < interfaces.length && !result; i++) {
	    result = interfaces[i].getName().contains(nameInterface);
	}
	if(!result && null != cls.getSuperclass()) {
	    result = classImplementsInterfaceByName(nameInterface, cls.getSuperclass());
	}
	return result;
    }


    /**
     * Devuelve la clase del generico contenido en un campo
     * 
     * @param dstClass
     * @param field
     * @return
     * @throws ClassNotFoundException
     */
    private Class<?> getClassOfGeneric(Field field) throws ClassNotFoundException {

	Type tipo = field.getGenericType();
	Class<?> clasz = null;
	if(tipo instanceof ParameterizedType) {
	    ParameterizedType pt = (ParameterizedType) tipo;
	    clasz = (Class<?>) pt.getActualTypeArguments()[0];
	}
	return clasz;
    }


    /**
     * Devuelve un Date a partir del patron de fecha con que se anota el string
     * 
     * @param dstField
     * @param srcField
     * @param str
     * @return
     * @throws ParseException
     */
    private Date stringToDate(Field dstField, Field srcField, String str) throws ParseException {

	Date result = null;
	if(srcField.isAnnotationPresent(MappingDateFormat.class)) {
	    String format = srcField.getAnnotation(MappingDateFormat.class).pattern();
	    result = stringToDate(dstField.getType(), str, format);
	}
	else {
	    throw new MappingException("Para mapear string a fecha debe anotarse el string con MappingDateFormat");
	}

	return result;
    }


    private Date stringToDate(Class<?> dstClass, String str, String pattern) throws ParseException {

	Date result;
	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	Date fechaUtil = sdf.parse(str);
	if(dstClass.equals(java.sql.Date.class)) {
	    result = new java.sql.Date(fechaUtil.getTime());
	}
	else {
	    result = fechaUtil;
	}
	return result;
    }


    /**
     * Devuelve un string resultado de aplicar el patron definido en
     * MappingNumberFormat o si no esta anotado el resultado del metodo toString
     * de srcValue
     * 
     * @param dstField
     * @param n
     * @return
     */
    private String numberToString(Field dstField, Number n) {

	String result = null;

	if(dstField.isAnnotationPresent(MappingNumberFormat.class)) {
	    String pattern = dstField.getAnnotation(MappingNumberFormat.class).pattern();
	    DecimalFormat df = new DecimalFormat(pattern);
	    result = df.format(n);

	}
	else {
	    result = n.toString();
	}
	return result;
    }


    /**
     * Devuelve la fecha como un string formateada segun el patron definido en
     * la anotacion MappingDateFormat
     * 
     * @param dstField
     * @param d
     * @return
     */
    private String dateToString(Field dstField, Date d) {

	String result = null;
	MappingDateFormat anotaFecha = dstField.getAnnotation(MappingDateFormat.class);
	if(anotaFecha != null) {
	    SimpleDateFormat sdf = new SimpleDateFormat(anotaFecha.pattern());
	    result = sdf.format(d);
	}
	else {
	    result = d.toString();
	}
	return result;
    }


    /**
     * Devuelve un Number a partir de un String
     * 
     * @param dstClass
     * @param str
     * @return
     * @throws ParseException
     */
    private Number stringToNumber(Class<?> dstClass, String str) throws ParseException {

	Number i = null;
	if(str != null) {
	    // Obtengo un number a partir de la conversion del string atendiendo
	    // al locale
	    Number n = nf.parse(str);
	    BigDecimal bd = new BigDecimal(n.toString());
	    if(dstClass.equals(BigDecimal.class)) {
		i = bd;
	    }
	    else if(dstClass.equals(Integer.class)) {
		i = bd.intValue();
	    }
	    else if(dstClass.equals(Double.class)) {
		i = bd.doubleValue();
	    }
	    else if(dstClass.equals(Float.class)) {
		i = bd.floatValue();
	    }
	    else if(dstClass.equals(Long.class)) {
		i = bd.longValue();
	    }
	    else if(dstClass.equals(Short.class)) {
		i = bd.shortValue();
	    }
	}

	return i;
    }

    private final static class Result {

	/**
	 * indica si se ha podido tenido exito en la conversion
	 */
	private boolean success;

	/**
	 * Objeto resultante de la conversion null es un valor correcto
	 */
	private Object value;


	private Result(boolean success, Object value) {

	    super();
	    this.success = success;
	    this.value = value;
	}
    }
}
