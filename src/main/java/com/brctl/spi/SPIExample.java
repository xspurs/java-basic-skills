package com.brctl.spi;

import java.util.ServiceLoader;

/**
 * SPI Example<br/>
 * Service configured in (classpath)META-INF/services/{full_package_path_name}, the format
 * is fixed.
 *
 * Usage from {@link ServiceLoader}:<br/>
 * <p><a name="format"> A service provider is identified by placing a
 * <i>provider-configuration file</i> in the resource directory
 * <tt>META-INF/services</tt>.</a>  The file's name is the fully-qualified <a
 * href="../lang/ClassLoader.html#name">binary name</a> of the service's type.
 * The file contains a list of fully-qualified binary names of concrete
 * provider classes, one per line.  Space and tab characters surrounding each
 * name, as well as blank lines, are ignored.  The comment character is
 * <tt>'#'</tt> (<tt>'&#92;u0023'</tt>,
 * <font style="font-size:smaller;">NUMBER SIGN</font>); on
 * each line all characters following the first comment character are ignored.
 * The file must be encoded in UTF-8.
 *
 * @author duanxiaoxing
 * @created 2018/8/2
 */
public class SPIExample {

    public static void main(String[] args) {
        ServiceLoader<HelloInterface> loader = ServiceLoader.load(HelloInterface.class);
        for (HelloInterface helloInterface : loader) {
            helloInterface.sayHello();
        }
    }

}
