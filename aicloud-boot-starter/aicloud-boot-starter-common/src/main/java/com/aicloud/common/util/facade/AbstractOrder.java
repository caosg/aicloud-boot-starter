/** 
 * @Copyright (c) 2018, 周天琪 johnny_ztq@163.com.com All Rights Reserved.
 * @PackageName:com.aicloud 
 * @Date:2018年2月8日上午9:39:02  
 * 
*/

package com.aicloud.common.util.facade;

import com.aicloud.common.util.tostring.ToString;
import com.aicloud.common.util.validation.AicloudValidation;

import javax.validation.ConstraintViolation;
import java.io.Serializable;
import java.util.Set;

/**
 * 抽象order（所有order的父类）
 */
public abstract class AbstractOrder implements Serializable {
    /**
     * 校验
     */
    public void check() {
        checkWithGroups();
    }

    /**
     * 分组校验
     *
     * @param groups 分组
     * @throws IllegalArgumentException 如果校验不通过
     */
    public final void checkWithGroups(Class... groups) {
        Set<ConstraintViolation<AbstractOrder>> violations = AicloudValidation.getValidator().validate(this, groups);
        if (violations.size() > 0) {
            // 校验不通过，构建错误信息
            StringBuilder errMsg = new StringBuilder();
            for (ConstraintViolation<AbstractOrder> violation : violations) {
                errMsg.append(violation.getPropertyPath().toString());
                errMsg.append(violation.getMessage() + ";");
            }
            errMsg.deleteCharAt(errMsg.length() - 1);
            throw new IllegalArgumentException(errMsg.toString());
        }
    }

    @Override
    public String toString() {
        return ToString.toString(this);
    }
}
