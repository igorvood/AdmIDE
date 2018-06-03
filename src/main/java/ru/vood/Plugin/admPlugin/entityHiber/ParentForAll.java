package ru.vood.Plugin.admPlugin.entityHiber;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.vood.ADMMain;
import ru.vood.Plugin.admPlugin.entityHiber.intf.ParentForAllServise;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public abstract class ParentForAll {

    public final static String SCHEMA = "VOOD";
    protected BigDecimal id;

    public static <S extends ParentForAll> S save(S s) {
        return s.save();
    }

    public static ParentForAllServise getServise(ParentForAll s) {
        return ParentForAll.getServise(s.getClass().toString());
    }

    public static ParentForAllServise getServise(Class s) {
        return ParentForAll.getServise(s.toString());
    }

    public static ParentForAllServise getServise(String cl) {
        StringTokenizer tokenizer = new StringTokenizer(cl, ".", false);
        String clazz = tokenizer.nextToken();
        String currentClazz = clazz;
        while (tokenizer.hasMoreElements()) {
            clazz = tokenizer.nextToken();
            currentClazz = clazz;
        }
        return ParentForAll.getGenXmlAppContext().getBean("jpa" + currentClazz + "Service", ParentForAllServise.class);
    }

    private static GenericXmlApplicationContext getGenXmlAppContext() {
        return ADMMain.getCtx();
    }

    /**
     * @return возвращает следующий ID из последовательности
     */
    public static BigDecimal nextId(EntityManager em) {
        return (BigDecimal) em.createNativeQuery("SELECT SEQ_ID.nextval from dual").getResultList().get(0);
    }

    public <S extends ParentForAll> S save() {
        ParentForAllServise service = getServise();
        setIdIfItNull(service.getEntityManager());
        return (S) service.save(this);
    }


    public void delete() {
        ParentForAllServise service = getServise();
        service.delete(this);
    }

    public void delete(Iterable<? extends ParentForAll> iterable) {
        ParentForAllServise service = getServise();
        service.delete(iterable);
    }

    ;

    public <S extends ParentForAllServise> S getServise() {
        return (S) getServise(this);
    }

    public <S extends ParentForAll> S setIdIfItNull(EntityManager em) {

        if (this.id == null) {
            this.id = (ParentForAll.nextId(em));
        }
        return (S) this;
    }

}
