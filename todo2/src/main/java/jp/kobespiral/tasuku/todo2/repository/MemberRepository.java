package jp.kobespiral.tasuku.todo2.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.kobespiral.tasuku.todo2.entity.Member;

@Repository
public interface MemberRepository extends CrudRepository<Member, String> {
  List<Member> findAll();
}
