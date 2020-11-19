package com.me.youtu_android.douyin.bean

import android.os.Parcel
import android.os.Parcelable

data class DataBean(
        val appendData: AppendData?,
        val logMessage: String?,
        val message: String?,
        val resultType: Int
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readParcelable<AppendData>(AppendData::class.java.classLoader),
            source.readString(),
            source.readString(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeParcelable(appendData, 0)
        writeString(logMessage)
        writeString(message)
        writeInt(resultType)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DataBean> = object : Parcelable.Creator<DataBean> {
            override fun createFromParcel(source: Parcel): DataBean = DataBean(source)
            override fun newArray(size: Int): Array<DataBean?> = arrayOfNulls(size)
        }
    }
}

data class AppendData(
        val list: List<VideoList>
) : Parcelable {
    constructor(source: Parcel) : this(
            source.createTypedArrayList(VideoList.CREATOR)!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeTypedList(list)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<AppendData> = object : Parcelable.Creator<AppendData> {
            override fun createFromParcel(source: Parcel): AppendData = AppendData(source)
            override fun newArray(size: Int): Array<AppendData?> = arrayOfNulls(size)
        }
    }
}

data class VideoList(
        val appendInfo: List<Any>,
        val content: String?,
        val contentType: Int,
        val courseId: Int,
        val courseType: Int,
        val hdCommentNum: Int,
        val hdId: Int,
        val hdLookSum: Int,
        val hdPraiseNum: Int,
        val hdTime: Long,
        val headImg: String?,
        val isCollect: Int,
        val isFocus: Int,
        val isPraise: Int,
        val isRemark: Int,
        val isSVIP: Int,
        val isV: Int,
        val share: VideoShare?,
        val tagList: List<Any>,
        val title: String?,
        val umid: String?,
        val umiid: Int,
        val userName: String?,
        val videoInfo: VideoInfo?,
        val workScore: Int
) : Parcelable {
    constructor(source: Parcel) : this(
            ArrayList<Any>().apply { source.readList(this as List<*>, Any::class.java.classLoader) },
            source.readString(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readLong(),
            source.readString(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readParcelable<VideoShare>(VideoShare::class.java.classLoader),
            ArrayList<Any>().apply { source.readList(this as List<*>, Any::class.java.classLoader) },
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readParcelable<VideoInfo>(VideoInfo::class.java.classLoader),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeList(appendInfo)
        writeString(content)
        writeInt(contentType)
        writeInt(courseId)
        writeInt(courseType)
        writeInt(hdCommentNum)
        writeInt(hdId)
        writeInt(hdLookSum)
        writeInt(hdPraiseNum)
        writeLong(hdTime)
        writeString(headImg)
        writeInt(isCollect)
        writeInt(isFocus)
        writeInt(isPraise)
        writeInt(isRemark)
        writeInt(isSVIP)
        writeInt(isV)
        writeParcelable(share, 0)
        writeList(tagList)
        writeString(title)
        writeString(umid)
        writeInt(umiid)
        writeString(userName)
        writeParcelable(videoInfo, 0)
        writeInt(workScore)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<VideoList> = object : Parcelable.Creator<VideoList> {
            override fun createFromParcel(source: Parcel): VideoList = VideoList(source)
            override fun newArray(size: Int): Array<VideoList?> = arrayOfNulls(size)
        }
    }
}


data class VideoShare(
        val shareDes: String?,
        val shareDesc: String?,
        val shareImg: String?,
        val shareTitle: String?,
        val shareUrl: String?
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(shareDes)
        writeString(shareDesc)
        writeString(shareImg)
        writeString(shareTitle)
        writeString(shareUrl)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<VideoShare> = object : Parcelable.Creator<VideoShare> {
            override fun createFromParcel(source: Parcel): VideoShare = VideoShare(source)
            override fun newArray(size: Int): Array<VideoShare?> = arrayOfNulls(size)
        }
    }
}

data class VideoInfo(
        val imgHeight: Int,
        val imgWidth: Int,
        val videoCoverUrl: String?,
        val videoDuration: String?,
        val videoGifUrl: String?,
        val videoUrl: String?
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(imgHeight)
        writeInt(imgWidth)
        writeString(videoCoverUrl)
        writeString(videoDuration)
        writeString(videoGifUrl)
        writeString(videoUrl)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<VideoInfo> = object : Parcelable.Creator<VideoInfo> {
            override fun createFromParcel(source: Parcel): VideoInfo = VideoInfo(source)
            override fun newArray(size: Int): Array<VideoInfo?> = arrayOfNulls(size)
        }
    }
}