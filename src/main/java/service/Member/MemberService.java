package service.Member;

import model.Member;

import java.util.List;

/**
 * Created by LulzimG on 29/12/16.
 */
public interface MemberService {

    List<Member> getProjectMembers(String projectId);

    Member getMember(String memberId);

    String addMember(Member member);

    String updateMember(Member member);

    String deleteMember(Member member);

    void insertBatch(List<Member> members,String projectId);
}
