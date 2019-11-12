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
     * @return 若匹配成功返回 验证成功的字符串位置，否则返回验证了字符串的长度
     */
    public Integer verify(char[] arr, int start){
        int i ;
        for(i = start; i<arr.length;i++ ){
            char temp = arr[i];
            if( temp == verifyStack.peek()){
                successStack.push( verifyStack.pop() );  //相等则出栈
                verifyState = true ; //激活状态
            }else{
                if(verifyState) { //说明被激活且匹配失败
                    resetStack();   //栈重置
                    verifyState = false;    //重置匹配状态
                }
            }
            if(getResult()){
                break;  //成功匹配
            }

        }
        return i;   //返回验证的

    }

    /**
     * 验证失败，恢复验证的初始状态
     */
    private void resetStack(){
        for(int i = 0 ; i <  successStack.size(); i++){
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
