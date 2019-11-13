package http;

import java.util.Stack;

public class HttpVerifyHeader {

    private Stack<Character> verifyStack;
    private boolean verifyState; //是否激活
    private Stack<Character> successStack;  //成功匹配的


    public HttpVerifyHeader(String regString){
        this(regString.toCharArray());
    }

    public HttpVerifyHeader(char[] regCharArr){
        successStack = new Stack<Character>(); //储存成功字符串
        verifyStack = new Stack<Character>();
        for(char c:regCharArr){
            verifyStack.push(c);
        }
        verifyState = false;    //验证器状态
    }

    /**
     *
     * @param arr
     * @param start
     * @return 若匹配到的字符串位置
     */
    public Integer verify(char[] arr, int start,int len){
        int i ;
        for(i = 0; i<len;i++ ){
            char temp = arr[start+i];
            if( temp == verifyStack.peek()){
                successStack.push( verifyStack.pop() );  //相等则出栈
                verifyState = true ; //激活状态
            }else{
                if(verifyState) { //说明被之前存在匹配但本次匹配失败
                    resetStack();   //栈重置
                    verifyState = false;    //重置匹配状态
                }
            }
            if(getResult()){
                return start+i;  //匹配成功的位置
            }

        }
        return start+i-1;   //返回验证的最后一个字符串位置

    }

    /**
     * 验证失败，恢复验证的初始状态
     */
    private void resetStack(){
        //必须这么写，因为 Stack进行下面操作的size时变化的
        int size = successStack.size();

        for(int i = 0 ; i <  size; i++){
            verifyStack.push( successStack.pop());
        }
    }

    /**
     * @return 是否验证成功
     */
    public boolean getResult(){
        return verifyStack.size() == 0 ? true : false;
    }


}
