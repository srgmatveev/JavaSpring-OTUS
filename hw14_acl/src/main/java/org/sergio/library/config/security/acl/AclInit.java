package org.sergio.library.config.security.acl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sergio.library.domain.security.acl.AclSid;
import org.sergio.library.secRepo.acl.AclSidRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.util.List;

@Profile("acl-init")
@Configuration
public class AclInit {
    @Value("${admin.full_role}")
    private String adminRole;


    @Autowired
    AclSidRepo aclSidRepo;

    @Autowired
    private SessionFactory sessionFactory;

    @PostConstruct
    public void setup() {

        Session session = sessionFactory.openSession();
        List<AclSid> lst = aclSidRepo.findAll();
        session.beginTransaction();
         lst.forEach(x->{

             x= (AclSid) session.merge(x);
            session.delete(x);
        });
        session.getTransaction().commit();

        session.beginTransaction();
        AclSid sid1 = new AclSid();
        // sid1.setId(10L);
        sid1.setPrincipal(1);
        sid1.setSid(adminRole);
        sid1 = aclSidRepo.save(sid1);
        System.out.println(sid1.getId());

        session.saveOrUpdate(sid1);
        session.getTransaction().commit();

        aclSidRepo.findAll().forEach(System.out::println);

    }
}
