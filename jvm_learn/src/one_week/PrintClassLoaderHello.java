package one_week;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * 将Hello.xlass文件进行反码打印输出
 */
public class PrintClassLoaderHello extends ClassLoader {

    private String path;

    public PrintClassLoaderHello(String path) {
        this.path = path;
    }

    public static void main(String[] args) {
        try {
            PrintClassLoaderHello printClassLoaderHello = new PrintClassLoaderHello("D:\\jike\\one_week");
            //加载hello对象
            Class<?> helloClass = printClassLoaderHello.loadClass("Hello");
            //Object o = helloClass.getDeclaredConstructor().newInstance();
            //创建hello对象
            Object obj = helloClass.newInstance();
            Method helloMethod = helloClass.getDeclaredMethod("hello");
            helloMethod.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] arrayByte = getArrayByte(name);
            return defineClass(name, arrayByte, 0, arrayByte.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    /**
     * 获取字符数组
     *
     * @param name
     * @return
     * @throws Exception
     */
    public byte[] getArrayByte(String name) throws Exception {
        name = name.replaceAll("\\.", "/");
        FileInputStream fis = new FileInputStream(path + File.separator + name + ".xlass");
        byte[] bs = new byte[fis.available()];
        fis.read(bs);
        fis.close();
        byte[] calculateByte = calculate(bs);

        return calculateByte;
    }
    /**
     * 反码计算
     *
     * @param bs
     * @return
     */
    public byte[] calculate(byte[] bs) {
        byte[] bytes = new byte[bs.length];
        for (int i = 0; i < bs.length; i++) {
            bytes[i] = (byte) (255 - bs[i]);
        }
        return bytes;
    }
}
