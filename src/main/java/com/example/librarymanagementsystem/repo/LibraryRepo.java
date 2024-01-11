package com.example.librarymanagementsystem.repo;

import com.example.librarymanagementsystem.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 11/01/24
 * @Time ➤➤➤ 11:37 am
 * @Project ➤➤➤ librarymanagementsystem
 */
@Repository
public interface LibraryRepo extends JpaRepository<Library,String> {
    Library findByEmail(String email);

    Library findByAdminEmail(String email);
}
