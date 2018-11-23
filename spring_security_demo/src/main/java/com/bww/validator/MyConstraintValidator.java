/**
 * 
 */
package com.bww.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

//	@Autowired
//	private HelloService helloService;
	
	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		System.out.println("my validator init");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
//		helloService.greeting("tom");
		System.out.println(value);
		return false;//false表示校验不通过， true表示校验通过  所有的校验逻辑在这里完成
	}

}
