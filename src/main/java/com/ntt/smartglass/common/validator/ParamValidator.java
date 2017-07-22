package com.ntt.smartglass.common.validator;

import javax.validation.*;
import java.util.*;

/**
 *パラメータチェック
 *
 */
public class ParamValidator {
    /**
     *
     * @param t parameter list
     * @param skipFields skipFields
     * @param <T> T
     * @return validator情報
     */
    public static <T> Map<String, ArrayList<String>> validator(T t, HashSet<String> skipFields) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        if (constraintViolations != null && constraintViolations.size() > 0) {
            Map<String, ArrayList<String>> mapErr = new HashMap<String, ArrayList<String>>();
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                for (Path.Node node : constraintViolation.getPropertyPath()) {
                    String fieldName = node.getName();
                    if (skipFields == null || !skipFields.contains(fieldName)) {
                        ArrayList<String> lst = mapErr.get(fieldName);
                        if (lst == null) {
                            lst = new ArrayList<String>();
                        }
                        lst.add(constraintViolation.getMessage());
                        mapErr.put(node.getName(), lst);
                    }
                }
            }
            return mapErr;
        }
        return null;
    }

    public static <T> Map<String, ArrayList<String>> validator(T t) {
        return validator(t, null);
    }
}
