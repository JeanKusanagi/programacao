package g12.ex1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Scanner;

/**
 * ClassInfo
 * 
 * @author Pedro Teixeira
 */

public class ClassInfo {

	// Instance Fields
	private Class<?> cl;

	// ----------------------------------------
	// Constructor
	public ClassInfo (String name) throws ClassNotFoundException {
		cl = Class.forName(name);
	}

	// ----------------------------------------
	// Methods
	/**
	 * Returns the class declaration
	 * @param cl Class to get the declarations from
	 * @return the class declaration
	 */
	public static String getHeader(Class<?> cl) {
		StringBuilder s = new StringBuilder();

		s.append(cl.getPackage());
		s.append(";\n\n");

		// Modifiers
		s.append(Modifier.toString(cl.getModifiers()));
		if (!cl.isInterface()) s.append(" class");

		// class simple name
		s.append(" ");
		s.append(cl.getSimpleName());

		// generics
		getGenerics(cl, s);

		// superclasses
		Class<?> superClass = cl.getSuperclass();
		if (superClass != null && !superClass.getSimpleName().equals("Object")) {
			s.append(" extends ");
			s.append(superClass.getSimpleName());
			getGenerics(superClass, s);
		}

		// interfaces
		Class<?>[] interfaceClasses = cl.getInterfaces();
		if (interfaceClasses.length != 0) {
			s.append(" implements ");
			for (int i = 0; i < interfaceClasses.length; i++) {
				s.append(interfaceClasses[i].getSimpleName());
				getGenerics(interfaceClasses[i], s);
				if (i != interfaceClasses.length - 1) s.append(", ");
			}
		}

		return s.toString();
	}	

	/**
	 * Returns the fields declarations, if there's any field. Inherited fields declarations are not returned. 
	 * @param cl Class to get the fields from
	 * @return the fields declarations, if there's any field, "// No fields" if there is no field declaration
	 */
	public static String getFields(Class<?> cl) {
		StringBuilder s = new StringBuilder();

		Field[] fields = cl.getDeclaredFields(); 
		if (fields.length > 0) {
			for (Field f : fields) {
				s.append("\t");

				s.append(Modifier.toString(f.getModifiers()));	// Modifiers
				s.append(" ");
				getType(f.getType(), s);						// type
				s.append(" ");										
				s.append(f.getName());							// FIELD name
				s.append(";\n");
			}
		}
		else 
			s.append("\t// No fields\n");

		return s.toString();
	}

	/**
	 * Returns the constructors declarations
	 * @param cl Class to get the declarations from
	 * @return the constructors declarations
	 */
	public static String getCtors(Class<?> cl) {
		StringBuilder s = new StringBuilder();

		Constructor<?>[] ctors = cl.getConstructors();
		getCtors(ctors, s);

		return s.toString();
	}

	/**
	 * Returns the methods declarations
	 * @param cl Class to get the declarations from
	 * @return the methods declarations
	 */
	public static String getMethods(Class<?> cl) {
		StringBuilder s = new StringBuilder();

		Method[] methods = cl.getDeclaredMethods();
		getMethods(methods, s);

		return s.toString();
	}

	// ------------------
	/**
	 * Lists and allows the user to choose the CTOR to create an instance of the given class 
	 * @param cl
	 * @return
	 * @throws Exception
	 */
	public static Object newInstance(String cl) throws Exception {
		return newInstance(Class.forName(cl));
	}

	/**
	 * Lists and allows the user to choose the CTOR to create an instance of the given class 
	 * @param cl
	 * @return
	 * @throws Exception
	 */
	public static Object newInstance(Class<?> cl) throws Exception {
		StringBuilder s = new StringBuilder();

		// List Ctors
		System.out.println("Available Ctors:");
		Constructor<?>[] ctors = getCtors(cl.getConstructors(), s, 0);
		System.out.println(s.toString());

		// Get Ctor number
		System.out.print("-> Choose CTOR (number from 1-#CTORs): ");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		int num = in.nextInt() - 1;

		// Get args
		Constructor<?> ctor = ctors[num];
		Parameter[] args = ctor.getParameters();

		if (args.length > 0) {
			Object[] argsToCtor = new Object[args.length];

			// from users
			System.out.print("-> Please input the arguments for the selected CTOR for " + cl.getSimpleName() + ":\n");
			for (int i = 0; i < args.length; i++) {
				argsToCtor[i] = getArg(args[i]);
			}

			return ctor.newInstance(argsToCtor);
		}

		return ctor.newInstance();
	}

	/**
	 * Lists and allows the user to choose the method of the given class to be invoked
	 * @param cl
	 * @return
	 * @throws Exception
	 */
	public static Object invokeMethod(String cl, Object obj) throws Exception {
		return invokeMethod(Class.forName(cl), obj);
	}

	/**
	 * Lists and allows the user to choose the method of the given class to be invoked 
	 * @param cl
	 * @return
	 * @throws Exception
	 */
	public static Object invokeMethod(Class<?> cl, Object obj) throws Exception {
		StringBuilder s = new StringBuilder();

		// List Methods
		System.out.println("Available Methods:");
		Method[] methods = getMethods(cl.getDeclaredMethods(), s, 0);
		System.out.println(s.toString());

		// Get Methods number
		System.out.print("-> Choose Method (number from 1-#Methods): ");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		int num = in.nextInt() - 1;

		// Get args
		Method method = methods[num];
		Parameter[] args = method.getParameters();
		Object[] argsToMethod = new Object[args.length];

		if (args.length > 0) {
			// from users
			System.out.print("-> Please input the arguments for the selected method for " + cl.getSimpleName() + ":\n");
			for (int i = 0; i < args.length; i++) {
				Parameter arg = args[i];
				argsToMethod[i] = getArg(arg);
			}
		}

		return method.invoke(obj, argsToMethod);		
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(getHeader(cl));
		s.append(" {\n\n\t// Fields\n");
		s.append(getFields(cl));
		s.append("\n\t// Constructors\n");
		s.append(getCtors(cl));
		s.append("\n\t// Methods\n");
		s.append(getMethods(cl));
		s.append("\n}");
		return s.toString();
	}

	// ----------------------------------------
	// Auxiliary Methods
	private static Constructor<?>[] getCtors(Constructor<?>[] ctors, StringBuilder s, int... op) {
		int i = 0;
		for (Constructor<?> ctor : ctors) {
			if (op.length > 0 && op[0] == 0) {					// op to print CTORs numbers in method newInstance
				i++;
				s.append(i);
				s.append(": ");
			}
			else s.append("\t");
			s.append(Modifier.toString(ctor.getModifiers()));	// Modifiers
			s.append(" ");		
			s.append(ctor.getDeclaringClass().getSimpleName()); // CTOR name
			s.append("(");					
			//Class<?>[] args = ctor.getParameterTypes();		// Args
			Parameter[] args = ctor.getParameters();
			getArgs(args, s);
			s.append(")");
			getExceptions(ctor.getExceptionTypes(), s);			// Exceptions
			s.append("\n");
		}
		return ctors;
	}

	private static Method[] getMethods(Method[] methods, StringBuilder s, int... op) {
		int j = 0;
		for (Method method : methods) {
			if (op.length > 0 && op[0] == 0) {					// op to print Methods numbers in method invokeMethod
				j++;
				s.append(j);
				s.append(": ");
			}
			else s.append("\t");

			/*Annotation[] annotations = method.getDeclaredAnnotations();	// Annotations
			for (Annotation a : annotations)
				s.append(a.annotationType().getName());
			 */		
			s.append(Modifier.toString(method.getModifiers()));	// Modifiers
			s.append(" ");			
			TypeVariable<?>[] argTypeNames = 						// Arg Generics
					method.getTypeParameters();
			if (argTypeNames.length > 0) {
				s.append("<");
				for (int i = 0; i < argTypeNames.length; i++) {
					s.append(argTypeNames[i].getTypeName());		// -> Generic Type Name
					getTypeBounds(argTypeNames[i].getBounds(), s);	// -> Generic Type Bounds

					if (i != argTypeNames.length - 1) s.append(", ");
				}
				s.append("> ");
			}
			getType(method.getReturnType(), s);					// Return type
			s.append(" ");		
			s.append(method.getName()); 						// Method name name
			s.append("(");					
			//Class<?>[] args = ctor.getParameterTypes();		// Args
			Parameter[] args = method.getParameters();
			getArgs(args, s);
			s.append(")");										
			getExceptions(method.getExceptionTypes(), s);		// Exceptions
			s.append("\n");
		}
		return methods;
	}

	private static void getGenerics(Class<?> c, StringBuilder s) {
		TypeVariable<?>[] typeVars = c.getTypeParameters();
		if (typeVars.length != 0) {
			s.append("<");
			for (int i = 0; i < typeVars.length; i++) {
				s.append(typeVars[i].getName());
				getTypeBounds(typeVars[i].getBounds(), s);
				if (i != typeVars.length - 1) s.append(", ");

			}
			s.append(">");
		}

	}

	private static void getType(Class<?> type, StringBuilder s) {
		s.append(type.getSimpleName());							// 	-> type name
		getGenerics(type, s);									// 	-> generics
	}

	private static void getArgs(Parameter[] args, StringBuilder s) {
		for (int i = 0; i < args.length; i++) {
			Parameter arg = args[i];
			s.append(Modifier.toString(arg.getModifiers()));	// modifiers (final)
			s.append("");
			getType(arg.getType(), s);							// type
			if (arg.isVarArgs()) 								// varArgs
				s.replace(s.length() - 2, s.length(), "..."); 									
			if (i != args.length - 1) s.append(", ");
		}
	}

	private static void getExceptions(Class<?>[] exceptions, StringBuilder s) {
		if (exceptions.length > 0) {
			s.append(" throws ");
			for (int i = 0; i < exceptions.length; i++) {
				Class<?> exception = exceptions[i];
				//s.append(Modifier.toString(arg.getModifiers()));	// modifiers
				//getGenerics(exception, s);						// generics
				//s.append(" ");										
				s.append(exception.getSimpleName());				// exception name
				if (i != exceptions.length - 1) s.append(", ");
			}
		}
	}

	private static void getTypeBounds(Type[] bounds, StringBuilder s) {
		if (bounds.length > 0) {					
			if (!bounds[0].getTypeName().contains("java.lang.Object")) s.append(" extends ");	// for when it only has 1 bound (Object)
			for (int j = 0; j < bounds.length; j++) {
				Type bound = bounds[j];
				if (!bound.getTypeName().contains("java.lang.Object")) {
					parseSimpleNameOfBoundsTypes(bound.getTypeName(), s);
					if (j != bounds.length - 1) s.append(" & ");
				}
			}
		}			
	}

	/** @author Luis Moura, Pedro Teixeira */
	private static void parseSimpleNameOfBoundsTypes (String simpleName, StringBuilder s) {
		String[] arr = simpleName.split(",");
		for(int i = 0; i < arr.length-1; i++){
			s.append(arr[i].substring(arr[i].lastIndexOf(".") + 1));
			s.append(", ");
		}
		s.append(arr[arr.length-1].substring(arr[arr.length-1].lastIndexOf(".") + 1, arr[arr.length-1].length()));
	}

	private static Object getArg(Parameter arg) throws Exception {
		Class<?> argType = arg.getType();
		String argTypeName = argType.getSimpleName();

		//System.out.println(argTypeName);
		System.out.print("\n" + argTypeName + "-> ");

		// if it's primitive or a String, get it from the console input
		if (argType.isPrimitive() || argTypeName.equals("String")) {
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			String toParse = in.nextLine();
			if 		(argTypeName.equals("int"))     return Integer.parseInt(toParse);		//int
			else if (argTypeName.equals("short"))   return Short.parseShort(toParse);		//short
			else if (argTypeName.equals("long"))    return Long.parseLong(toParse);			//long
			else if (argTypeName.equals("double"))  return Double.parseDouble(toParse);		//double
			else if (argTypeName.equals("float"))   return Float.parseFloat(toParse);		//float
			else if (argTypeName.equals("boolean")) return Boolean.parseBoolean(toParse);	//boolean
			else if (argTypeName.equals("byte"))    return Byte.parseByte(toParse);			//byte
			else if (argTypeName.equals("char"))    return toParse.charAt(0);				//char															
			else if (argTypeName.equals("String"))  return toParse;							//String
		}
		// else, if it's an object, create it
		return newInstance(arg.getType());

	}
}	

