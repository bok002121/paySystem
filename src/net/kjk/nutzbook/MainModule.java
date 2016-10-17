package net.kjk.nutzbook;


import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Localization;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@SetupBy(value = MainSetup.class)
@IocBy(type=ComboIocProvider.class, args={"*js", "ioc/",
    "*anno", "net.kjk.nutzbook",
    "*tx"})
@Localization(value="msg/", defaultLocalizationKey="zh-CN")
@Ok("json:full")
@Fail("jsp:jsp.500")
@Modules(scanPackage=true)

//@Filters(@By(type = CheckSession.class, args ={ "me", "/" }))
public class MainModule
{

}
