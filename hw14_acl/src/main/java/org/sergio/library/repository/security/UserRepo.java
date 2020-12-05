package org.sergio.library.repository.security;

import org.sergio.library.domain.Author;
import org.sergio.library.domain.security.SecUser;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepo")
@Profile("security_init")
public interface UserRepo extends MongoRepository<SecUser, String> {
    @Query("{ 'Login' : {$regex: '^?0$', $options: 'i' }}")
    List<SecUser> findByLogin(final String Name);
}
