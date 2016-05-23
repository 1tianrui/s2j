import com.alibaba.fastjson.JSON;
import createIbaits.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import result.CommonResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jnkmhbl on 16/5/18.
 */
@RestController
@EnableAutoConfiguration
public class HelloWorld {
    private static ClassEntity classEntity ;
    private static TableEntity tableEntity ;
    private static String packagePrefix;
    private static String resultId ;
    private List<SqlClassMapping> poClasses = new ArrayList<SqlClassMapping>() ;
    @RequestMapping("/")
    String home(){
        return "hello";
    }


    //创建成功后会返回一个属性列表，作为后续选择的一个列表，所有的筛选都在这个基础上
    @RequestMapping("/uploadCreateTable")
    Object parseTable(@RequestParam(value = "tablesql") String tableSql){
        SqlResolve resolve = new SqlResolve();
         tableEntity = resolve.solve(tableSql);
         ClassCreator classCreator = new ClassCreator();
         classEntity = classCreator.createClassEntityFromTable(tableEntity);
         List<String> columns = new ArrayList<String>();
         for(ColumnEntry entry : tableEntity.getColumn()){
             columns.add(entry.getName());
         }
        return CommonResult.buildResult(1, JSON.toJSONString(columns)).toJSON();
    }

    @RequestMapping("/createPo")
    Object createClass(
                       @RequestParam(value = "packagePrefix")String packagePrefix,
                       @RequestParam(value ="endName")String endName,
                       @RequestParam(value = "location")String location)throws  Exception{
        classEntity.setClassName(classEntity.getClassName() + endName);
        ClassGenetor genetor = new ClassGenetor(classEntity);
        genetor.geneClass(packagePrefix,location);
        return  CommonResult.buildResult(1, "").toJSON();
    }



//重点看SqlParam类
    @RequestMapping("/createMapper")
    Object createMapper(@RequestParam(value = "location")String location,
                        @RequestParam(value = "resultId")String resultMapId,
                         @RequestParam(value = "sqllist")String params,
                         @RequestParam(value="mapper")String fileName
                    )throws  Exception{
        List<SqlParam> paramList = JSON.parseArray(params, SqlParam.class);
        IbatiesMapperCreator creator = new IbatiesMapperCreator(classEntity,tableEntity);
        creator.startBuild(resultMapId, location + classEntity.getClassName());
        for(SqlParam  param : paramList){
            if(param.getType() == SqlParamCode.INSERT){
                creator.buildInsert(param.getId());
                continue ;
            }

            if(param.getType() == SqlParamCode.UPDATE){
                creator.buildUpdate(param.getId(),param.getPreIndex(),param.getCondition());
                continue;
            }
            if(param.getType() == SqlParamCode.SELECT){
                creator.buildSelect(resultId,param.getId(),param.getPreIndex(),param.getCondition(),false);
            }
        }
        creator.endBuild();
        creator.wirteToFile(fileName);
        return "";
    }

    public static void main(String [] args){
        SpringApplication.run(HelloWorld.class,args);
    }
}
