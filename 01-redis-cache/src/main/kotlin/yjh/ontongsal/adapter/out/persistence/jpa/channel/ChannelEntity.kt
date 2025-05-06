package yjh.ontongsal.adapter.out.persistence.jpa.channel

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.ManyToOne
import jakarta.persistence.JoinColumn
import java.time.LocalDateTime
import yjh.ontongsal.adapter.out.persistence.jpa.member.MemberEntity
import yjh.ontongsal.domain.channel.Channel
import yjh.ontongsal.domain.channel.ChannelSnippet
import yjh.ontongsal.domain.channel.ChannelStatistics
import yjh.ontongsal.domain.member.ContentOwner

@Table(name = "channels")
@Entity
class ChannelEntity(
    @Id
    var id: String,

    @Column(name = "title")
    var title: String,

    @Column(name = "description")
    var description: String,

    @Column(name = "published_at")
    var publishedAt: LocalDateTime,

    @Column(name = "thumbnail_url")
    var thumbnailUrl: String,

    @Column(name = "view_count")
    var viewCount: Int,

    @Column(name = "video_count")
    var videoCount: Int,

    @Column(name = "subscriber_count")
    var subscriberCount: Int,

    @Column(name = "comment_count")
    var commentCount: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: MemberEntity,

    @Column(name = "member_name")
    var memberName: String
) {
    companion object {
        fun from(channel: Channel): ChannelEntity {
            return ChannelEntity(
                id = channel.id,
                title = channel.snippet.title,
                description = channel.snippet.description,
                publishedAt = channel.snippet.publishedAt,
                thumbnailUrl = channel.snippet.thumbnailUrl,
                viewCount = channel.statistics.viewCount,
                videoCount = channel.statistics.videoCount,
                subscriberCount = channel.statistics.subscriberCount,
                commentCount = channel.statistics.commentCount,
                member = MemberEntity.from(channel.contentOwner),
                memberName = channel.contentOwner.name
            )
        }
    }

    fun toModel(): Channel {
        return Channel(
            id = this.id,
            snippet = ChannelSnippet(
                title = this.title,
                description = this.description,
                publishedAt = this.publishedAt,
                thumbnailUrl = this.thumbnailUrl
            ),
            statistics = ChannelStatistics(
                viewCount = this.viewCount,
                videoCount = this.videoCount,
                subscriberCount = this.subscriberCount,
                commentCount = this.commentCount
            ),
            contentOwner = ContentOwner(
                memberId = this.member.id,
                name = this.memberName
            )
        )
    }
}