package org.sergio.library.secRepo.acl;

import org.hibernate.SessionFactory;
import org.sergio.library.domain.security.acl.AclSid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AclSidRepo extends JpaRepository<AclSid, Long> {

}
