import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.javatuples.Pair;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ORMQueries {

    SessionFactory sessionFactory;

    public ORMQueries() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    private List select(@org.intellij.lang.annotations.Language("HQL") String queryString) {

        Session session = null;
        try {

            //session = sessionFactory.getCurrentSession();
            session = sessionFactory.openSession();
            Transaction tr = session.beginTransaction();
            Query query = session.createQuery(queryString);
            List result = query.getResultList();

            tr.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    private Object selectSingle(@org.intellij.lang.annotations.Language("HQL") String queryString) {
        return select(queryString).get(0);
    }

    public List<Pupil> GetAllPupilsInfo() {
        List<Pupil> result = select("from Pupil");
        return result;
    }

    public List<Pupil> GetPupilsSortedByIdDESC() {
        List<Pupil> result = select("from Pupil order by id desc ");
        return result;
    }
    public List<Pupil> GetPupilsSortedByNameASC() {
        List<Pupil> result = select("from Pupil order by name asc ");
        return result;
    }
    public List<Pupil> GetPupilsId5() {
        List<Pupil> result = select("from Pupil where  id=5");
        return result;
    }
    public List<Pupil> GetPupilsNameLikeM() {
        List<Pupil> result = select("from Pupil where name like 'М%'");
        return result;
    }
    public List<Pupil> GetPupilsWhereGrade5() {
        List<Pupil> result = select("from Pupil where grade=5");
        return result;
    }
    public List<Pupil> GetPupilsLast5() {
        List<Pupil> result = select("from Pupil order by id asc");
        return result.subList(result.size() - 5,result.size());
    }
    public Integer[] GetPupilsMinMaxWeight() {
        Object[] result = (Object[]) selectSingle("select min(weight), max(weight) from Pupil");
        return Arrays.stream(result).toArray(Integer[]::new);
    }
    public Long GetCountPupilsWithHeightNoLess128() {
        Long result = (Long) selectSingle("select count(id) from Pupil where height > 128");
        return result;
    }
    public Long GetWeightPupilsWithHeight120() {
        Long result = (Long) selectSingle("select sum(weight) from Pupil where height = 120");
        return result;
    }
    public List<PupilAndSchool> GetPupilsAndSchool() {
        List<PupilAndSchool> result = select("select " +
                "new PupilAndSchool(p.id,p.surname,p.name,p.fathername,p.birthday,p.grade,s.sch_name) " +
                "from Pupil p "+
                "left join School s " +
                "on p.school_id=s.id "+
                "order by p.id asc");
        return result;
    }
    public List<Pair<Pupil, School>> GetPupilsAndSchoolAllId1and5() {
        List<Pair<Pupil, School>> result = select("select new org.javatuples.Pair(p, s) "+
                "from Pupil p "+
                "inner join School s "+
                "on p.school_id=s.id "+
                "where s.id IN (1, 5)");
        return result;
    }

}

class HibernateUtil {
    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        try{
            Configuration configuration = new Configuration();
            configuration.configure(); //"hibernate.cfg.xml"
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().configure();
            sessionFactory = configuration.buildSessionFactory(builder.build());
            return sessionFactory;
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = buildSessionFactory();
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}