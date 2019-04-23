package fi.tuni.blogproject;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * Class for making a randomized id for all objects in a String format.
 */
public class IDGenerator implements IdentifierGenerator {
    /**
     * Generates the random id String.
     *
     * @param sharedSessionContractImplementor Defines the internal contract
     *                                         shared between Session and StatelessSession.
     * @param o Object, the id is generated for.
     * @return Random id String.
     * @throws HibernateException Base throwable type for hibernate.
     */
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return UUID.randomUUID().toString().replace("-","");
    }
}
