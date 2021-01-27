package br.com.zup.casadocodigo.validacao.beanValidations;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {

    private String domainAttribute;
    private Class<?> klass;
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ExistsId constraintAnnotation) {
        this.domainAttribute = constraintAnnotation.fieldName();
        this.klass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        if (o == null) {
            return true;
        }
        Query query = manager.createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + " = :value");
        query.setParameter("value", o);
        List<?> list = query.getResultList();
        Assert.state(list.size() >= 0, "Id não encontrado");

        return !list.isEmpty();
    }
}