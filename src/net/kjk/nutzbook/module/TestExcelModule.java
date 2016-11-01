package net.kjk.nutzbook.module;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.kjk.nutzbook.toolkit.ReadExcel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.nutz.dao.DaoException;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.impl.AdaptorErrorContext;
import org.nutz.mvc.upload.FieldMeta;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

@IocBean
@Filters()
@At("/test2")
public class TestExcelModule extends BaseModule
{
	@At
	@AdaptBy(type=UploadAdaptor.class, args={"${app.root}/WEB-INF/tmp", "8192", "utf-8", "20000", "102400"})
    @POST
    public Object inPerson(@Param("Filedata")TempFile tf,
    		HttpServletRequest req,  
            AdaptorErrorContext err) throws IOException
	{
		NutMap re = new NutMap();
		String msg = null;
        if (err != null && err.getAdaptorErr() != null) {
            msg = "size not good";
        } else if (tf == null) {
            msg = "empty";
        } else {
            try {
          	
            	File f = tf.getFile();                       
            	// 这个是保存的临时文件   
            	FieldMeta meta = tf.getMeta();               
            	// 这个原本的文件信息   
            	String oldName = meta.getFileLocalName();
            	
            	System.out.println(oldName);
            	
            	String allName = meta.getFileLocalPath();
            	System.out.println(allName);
            	
            	String path = f.getPath();
            	System.out.println(path);
            	
            	String[] titles = ReadExcel.readExcelTitle(path);
            	System.out.println(Json.toJson(titles));
            	
            	Map<Integer, String> content = ReadExcel.readExcelContent(path);
            	System.out.println(Json.toJson(content));
            	
            	msg = Json.toJson(titles);
            	
            } catch(DaoException e) {
                msg = "system error";
            } catch (Throwable e) {
            	msg = "system error";
            }
        }
        System.out.println(msg);
        return re.setv("msg",msg);
	}
	
	/*
	 * 操作excel的函数
	 */
	
}
