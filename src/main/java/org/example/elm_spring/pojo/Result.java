package org.example.elm_spring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一响应结果封装类。
 * 用于所有 Controller 接口的统一返回格式，包含响应码、描述信息和返回数据。
 *
 * <ul>
 *   <li>{@code code} - 响应码：1 表示成功，0 表示失败</li>
 *   <li>{@code msg} - 响应描述信息</li>
 *   <li>{@code data} - 响应携带的数据，失败时为 null</li>
 * </ul>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;//响应码，1 代表成功; 0 代表失败
    private String msg;  //响应信息 描述字符串
    private Object data; //返回的数据

    /**
     * 增删改操作成功时的响应，不携带数据。
     *
     * @return code=1, msg="success", data=null 的 Result 对象
     */
    public static Result success(){
        return new Result(1,"success",null);
    }

    /**
     * 查询操作成功时的响应，携带返回数据。
     *
     * @param data 要返回给前端的数据
     * @return code=1, msg="success", data=data 的 Result 对象
     */
    public static Result success(Object data){
        return new Result(1,"success",data);
    }

    /**
     * 操作失败时的响应，携带错误描述。
     *
     * @param msg 错误描述信息
     * @return code=0, msg=msg, data=null 的 Result 对象
     */
    public static Result error(String msg){
        return new Result(0,msg,null);
    }
}
