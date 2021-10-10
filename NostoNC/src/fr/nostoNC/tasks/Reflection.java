package fr.nostoNC.tasks;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;

public final class Reflection {
  private static String OBC_PREFIX = Bukkit.getServer().getClass().getPackage().getName();
  
  private static String NMS_PREFIX = OBC_PREFIX.replace("org.bukkit.craftbukkit", "net.minecraft.server");
  
  private static String VERSION = OBC_PREFIX.replace("org.bukkit.craftbukkit", "").replace(".", "");
  
  private static Pattern MATCH_VARIABLE = Pattern.compile("\\{([^\\}]+)\\}");
  
  public static <T> FieldAccessor<T> getField(Class<?> paramClass, String paramString, Class<T> paramClass1) {
    return getField(paramClass, paramString, paramClass1, 0);
  }
  
  public static <T> FieldAccessor<T> getField(String paramString1, String paramString2, Class<T> paramClass) {
    return getField(getClass(paramString1), paramString2, paramClass, 0);
  }
  
  public static <T> FieldAccessor<T> getField(Class<?> paramClass, Class<T> paramClass1, int paramInt) {
    return getField(paramClass, null, paramClass1, paramInt);
  }
  
  public static <T> FieldAccessor<T> getField(String paramString, Class<T> paramClass, int paramInt) {
    return getField(getClass(paramString), paramClass, paramInt);
  }
  
  private static <T> FieldAccessor<T> getField(Class<?> paramClass, String paramString, Class<T> paramClass1, int paramInt) {
    for (Field field : paramClass.getDeclaredFields()) {
      if ((paramString == null || field.getName().equals(paramString)) && paramClass1.isAssignableFrom(field.getType()) && paramInt-- <= 0) {
        field.setAccessible(true);
        return new FieldAccessor<T>() {
            @SuppressWarnings("unchecked")
			public T get(Object param1Object) {
              try {
                return (T)field.get(param1Object);
              } catch (IllegalAccessException illegalAccessException) {
                throw new RuntimeException("Cannot access reflection.", illegalAccessException);
              } 
            }
            
            public void set(Object param1Object1, Object param1Object2) {
              try {
                field.set(param1Object1, param1Object2);
              } catch (IllegalAccessException illegalAccessException) {
                throw new RuntimeException("Cannot access reflection.", illegalAccessException);
              } 
            }
            
            public boolean hasField(Object param1Object) {
              return field.getDeclaringClass().isAssignableFrom(param1Object.getClass());
            }
          };
      } 
    } 
    if (paramClass.getSuperclass() != null)
      return getField(paramClass.getSuperclass(), paramString, paramClass1, paramInt); 
    throw new IllegalArgumentException("Cannot find field with type " + paramClass1);
  }
  
  public static interface FieldAccessor<T> {
    T get(Object param1Object);
    
    void set(Object param1Object1, Object param1Object2);
    
    boolean hasField(Object param1Object);
  }
  
  public static MethodInvoker getMethod(String paramString1, String paramString2, Class<?>... paramVarArgs) {
    return getTypedMethod(getClass(paramString1), paramString2, null, paramVarArgs);
  }
  
  public static MethodInvoker getMethod(Class<?> paramClass, String paramString, Class<?>... paramVarArgs) {
    return getTypedMethod(paramClass, paramString, null, paramVarArgs);
  }
  
  public static MethodInvoker getTypedMethod(Class<?> paramClass1, String paramString, Class<?> paramClass2, Class<?>... paramVarArgs) {
    for (Method method : paramClass1.getDeclaredMethods()) {
      if ((paramString == null || method.getName().equals(paramString)) && (paramClass2 == null || method
        .getReturnType().equals(paramClass2)) && 
        Arrays.equals((Object[])method.getParameterTypes(), (Object[])paramVarArgs)) {
        method.setAccessible(true);
        return new MethodInvoker() {
            public Object invoke(Object param1Object, Object... param1VarArgs) {
              try {
                return method.invoke(param1Object, param1VarArgs);
              } catch (Exception exception) {
                throw new RuntimeException("Cannot invoke method " + method, exception);
              } 
            }
          };
      } 
    } 
    if (paramClass1.getSuperclass() != null)
      return getMethod(paramClass1.getSuperclass(), paramString, paramVarArgs); 
    throw new IllegalStateException(String.format("Unable to find method %s (%s).", new Object[] { paramString, Arrays.asList(paramVarArgs) }));
  }
  
  public static interface MethodInvoker {
    Object invoke(Object param1Object, Object... param1VarArgs);
  }
  
  public static ConstructorInvoker getConstructor(String paramString, Class<?>... paramVarArgs) {
    return getConstructor(getClass(paramString), paramVarArgs);
  }
  
  public static ConstructorInvoker getConstructor(Class<?> paramClass, Class<?>... paramVarArgs) {
    for (Constructor<?> constructor : paramClass.getDeclaredConstructors()) {
      if (Arrays.equals((Object[])constructor.getParameterTypes(), (Object[])paramVarArgs)) {
        constructor.setAccessible(true);
        return new ConstructorInvoker() {
            public Object invoke(Object... param1VarArgs) {
              try {
                return constructor.newInstance(param1VarArgs);
              } catch (Exception exception) {
                throw new RuntimeException("Cannot invoke constructor " + constructor, exception);
              } 
            }
          };
      } 
    } 
    throw new IllegalStateException(String.format("Unable to find constructor for %s (%s).", new Object[] { paramClass, Arrays.asList(paramVarArgs) }));
  }
  
  public static interface ConstructorInvoker {
    Object invoke(Object... param1VarArgs);
  }
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
public static Class<Object> getUntypedClass(String paramString) {
    return (Class)getClass(paramString);
  }
  
  public static Class<?> getClass(String paramString) {
    return getCanonicalClass(expandVariables(paramString));
  }
  
  public static Class<?> getMinecraftClass(String paramString) {
    return getCanonicalClass(NMS_PREFIX + "." + NMS_PREFIX);
  }
  
  public static Class<?> getCraftBukkitClass(String paramString) {
    return getCanonicalClass(OBC_PREFIX + "." + OBC_PREFIX);
  }
  
  private static Class<?> getCanonicalClass(String paramString) {
    try {
      return Class.forName(paramString);
    } catch (ClassNotFoundException classNotFoundException) {
      throw new IllegalArgumentException("Cannot find " + paramString, classNotFoundException);
    } 
  }
  
  private static String expandVariables(String paramString) {
    StringBuffer stringBuffer = new StringBuffer();
    Matcher matcher = MATCH_VARIABLE.matcher(paramString);
    while (matcher.find()) {
      String str1 = matcher.group(1);
      String str2 = "";
      if ("fr/cocoraid/prodigynightclub/nms".equalsIgnoreCase(str1)) {
        str2 = NMS_PREFIX;
      } else if ("obc".equalsIgnoreCase(str1)) {
        str2 = OBC_PREFIX;
      } else if ("version".equalsIgnoreCase(str1)) {
        str2 = VERSION;
      } else {
        throw new IllegalArgumentException("Unknown variable: " + str1);
      } 
      if (str2.length() > 0 && matcher.end() < paramString.length() && paramString.charAt(matcher.end()) != '.')
        str2 = str2 + "."; 
      matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement(str2));
    } 
    matcher.appendTail(stringBuffer);
    return stringBuffer.toString();
  }
}
