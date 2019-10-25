package com.mmcro.cms.comon;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CmsControllerAdvice {
	
	@ResponseBody
	@ExceptionHandler(value = CmsException.class)       
	//使用@ExceptionHandler修饰后会作用在所有的@RequestMapping上。
	public ResultMsg myErrorHandler(CmsException ex) {
	    return new ResultMsg(ex.hashCode(), ex.getMessage(), "");
	}
	
    /*@ExceptionHandler(value = MyException.class)
    public ModelAndView myErrorHandler(MyException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("code", ex.getCode());
        modelAndView.addObject("msg", ex.getMsg());
        return modelAndView;
    }*/
}
