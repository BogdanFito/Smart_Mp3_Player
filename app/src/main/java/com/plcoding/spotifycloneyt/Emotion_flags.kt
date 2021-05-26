package com.plcoding.spotifycloneyt

import com.plcoding.spotifycloneyt.facedetector.FaceDetectorProcessor

class EmotionFlags(
        val isSmile: Boolean,
        val isLeftEyeOpen: Boolean,
        val isRightEyeOpen: Boolean
)

class EmotionFlagsEstimator {

    fun estimate(emotion: FaceDetectorProcessor.Emotion): EmotionFlags {
        return EmotionFlags(
                isSmile = emotion.smileProbability > 0.5,
                isLeftEyeOpen = emotion.leftEyeOpenProbability > 0.5,
                isRightEyeOpen = emotion.rightEyeOpenProbability > 0.5
        )
    }
}
