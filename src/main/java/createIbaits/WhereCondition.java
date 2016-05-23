package createIbaits;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianrui03 on 16/5/16.
 */
public class WhereCondition {
    //前端展示一个列表，展示所有属性列表，每个属性后面可以选择 ">" "<" "=" "in" ，如果不是in则这个index放在judgeColumn里面并将对应的符号放在operators里面，否则放在inList里面。

    private List<Integer> judgeColumnIndexes ;
    private List<String>  judgeOperators;
    private List<Integer> inListColumnIndexes ;
    public WhereCondition(){
        judgeColumnIndexes = new ArrayList<Integer>();
        judgeOperators = new ArrayList<String>();
        inListColumnIndexes = new ArrayList<Integer>();
    }

    public List<Integer> getJudgeColumnIndexes() {
        return judgeColumnIndexes;
    }

    public void setJudgeColumnIndexes(List<Integer> judgeColumnIndexes) {
        this.judgeColumnIndexes = judgeColumnIndexes;
    }

    public List<String> getJudgeOperators() {
        return judgeOperators;
    }

    public void setJudgeOperators(List<String> judgeOperators) {
        this.judgeOperators = judgeOperators;
    }

    public List<Integer> getInListColumnIndexes() {
        return inListColumnIndexes;
    }

    public void setInListColumnIndexes(List<Integer> inListColumnIndexes) {
        this.inListColumnIndexes = inListColumnIndexes;
    }
}
