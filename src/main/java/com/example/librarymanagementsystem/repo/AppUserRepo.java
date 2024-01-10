package com.example.librarymanagementsystem.repo;

import com.example.librarymanagementsystem.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 09/01/24
 * @Time ➤➤➤ 4:26 pm
 * @Project ➤➤➤ librarymanagementsystem
 */
@Repository
public interface AppUserRepo extends JpaRepository<AppUser,String> {
    AppUser findByEmail(String userName);

    AppUser findByUserName(String userName);

    AppUser findByContactNumber(String userName);
}
