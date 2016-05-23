package createIbaits;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jnkmhbl on 16/5/18.
 */

/**
 * 一个sql语句可以拆成两个部分  insert / update /select   part1  from ....  where part2
 */
public class SqlParam {



    //参见  SqlParamCode
    private int type ;
    //part 2
    private WhereCondition condition;
    //part1  前端提供一个在updateSql可以选择里面所有的属性，选择后传给我index List 从0----size -1
    private List<Integer> preIndex ;
    private String id ;

    public SqlParam(){
        type =-1;
        condition = new WhereCondition();
        preIndex = new ArrayList<Integer>();
        id ="";
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Integer> getPreIndex() {
        return preIndex;
    }

    public void setPreIndex(List<Integer> preIndex) {
        this.preIndex = preIndex;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public WhereCondition getCondition() {
        return condition;
    }

    public void setCondition(WhereCondition condition) {
        this.condition = condition;
    }
}
