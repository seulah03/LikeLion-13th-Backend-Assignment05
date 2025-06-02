package com.likelion.likelioncrud.post.application;

import com.likelion.likelioncrud.member.domain.Member;
import com.likelion.likelioncrud.member.domain.repository.MemberRepository;
import com.likelion.likelioncrud.post.api.dto.request.PostSaveRequestDto;
import com.likelion.likelioncrud.post.api.dto.request.PostUpdateRequestDto;
import com.likelion.likelioncrud.post.api.dto.response.PostInfoResponseDto;
import com.likelion.likelioncrud.post.api.dto.response.PostListResponseDto;
import com.likelion.likelioncrud.post.domain.Post;
import com.likelion.likelioncrud.post.domain.repository.PostRepository;
import com.likelion.likelioncrud.tag.domain.Tag;
import com.likelion.likelioncrud.tag.domain.repository.TagRepository;
import com.likelion.likelioncrud.posttag.domain.PostTag;
import com.likelion.likelioncrud.posttag.domain.repository.PostTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final PostTagRepository postTagRepository;

    // 게시물 저장
    @Transactional
    public void postSave(PostSaveRequestDto dto) {
        Member member = memberRepository.findById(dto.memberId())
                .orElseThrow(() -> new IllegalArgumentException("멤버를 찾을 수 없습니다. id=" + dto.memberId()));

        Post post = Post.builder()
                .title(dto.title())
                .contents(dto.contents())
                .member(member)
                .build();

        postRepository.save(post);

        // 태그 이름 리스트가 null이 아니고 비어있지 않으면
        if (dto.tagIds() != null && !dto.tagIds().isEmpty()) {
            for (String tagName : dto.tagIds()) {
                Tag tag = tagRepository.findByName(tagName)
                        .orElseGet(() -> tagRepository.save(new Tag(tagName)));

                PostTag postTag = PostTag.builder()
                        .post(post)
                        .tag(tag)
                        .build();
                postTagRepository.save(postTag);
            }
        }
    }

    // 작성자 기준 게시글 목록 조회
    public PostListResponseDto postFindMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("멤버를 찾을 수 없습니다. id=" + memberId));

        List<Post> posts = postRepository.findByMember(member);

        List<PostInfoResponseDto> dtoList = posts.stream()
                .map(post -> {
                    List<String> tagNames = postTagRepository.findByPost(post).stream()
                            .map(pt -> pt.getTag().getName())
                            .toList();
                    return PostInfoResponseDto.from(post, tagNames);
                })
                .toList();

        return PostListResponseDto.from(dtoList);
    }

    // 게시글 수정
    @Transactional
    public void postUpdate(Long postId, PostUpdateRequestDto dto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다. id=" + postId));

        post.update(dto);

        // 기존 태그 삭제
        postTagRepository.deleteByPost(post);

        // 새 태그들 저장
        if (dto.tagIds() != null && !dto.tagIds().isEmpty()) {
            for (String tagName : dto.tagIds()) {
                Tag tag = tagRepository.findByName(tagName)
                        .orElseGet(() -> tagRepository.save(new Tag(tagName)));

                PostTag postTag = PostTag.builder()
                        .post(post)
                        .tag(tag)
                        .build();
                postTagRepository.save(postTag);
            }
        }
    }

    // 게시글 삭제
    @Transactional
    public void postDelete(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다. id=" + postId));

        postTagRepository.deleteByPost(post);
        postRepository.delete(post);
    }
}
