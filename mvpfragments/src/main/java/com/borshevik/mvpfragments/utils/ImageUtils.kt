package com.borshevik.mvpfragments.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File


object ImageUtils {

    fun decodeSampledBitmapFromPath(filePath: String, reqWidth: Int, reqHeight: Int): Bitmap {
        // First decode with inJustDecodeBounds=true to check dimensions
        return BitmapFactory.Options().run {
            inJustDecodeBounds = true
            BitmapFactory.decodeFile(filePath, this)

            // Calculate inSampleSize
            inSampleSize = calculateInSampleSize(this, reqWidth, reqHeight)

            // Decode bitmap with inSampleSize set
            inJustDecodeBounds = false

            BitmapFactory.decodeFile(filePath, this)
        }
    }

    fun decodeSampledBitmapFromResource(res: Resources, resId: Int, reqWidth: Int, reqHeight: Int): Bitmap {
        // First decode with inJustDecodeBounds=true to check dimensions
        return BitmapFactory.Options().run {
            inJustDecodeBounds = true
            BitmapFactory.decodeResource(res, resId, this)

            // Calculate inSampleSize
            inSampleSize = calculateInSampleSize(this, reqWidth, reqHeight)

            // Decode bitmap with inSampleSize set
            inJustDecodeBounds = false

            BitmapFactory.decodeResource(res, resId, this)
        }
    }

    fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        // Raw height and width of image
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }

    fun updateAndSavePhotoSizeByPath(filePath: String, reqWidth: Int, reqHeight: Int) {

        val bitmap = createScalledBitmap(filePath, reqWidth, reqHeight)

        val bos = ByteArrayOutputStream()
        bitmap.compress(CompressFormat.JPEG, 100, bos)
        val bitmapdata = bos.toByteArray()
        val bs = ByteArrayInputStream(bitmapdata)

        var file: File = File(filePath)
        file.copyInputStreamToFile(bs)

        bs.close()
        bitmap.recycle()
    }

    fun createScalledBitmap(filePath: String, reqWidth: Int, reqHeight: Int): Bitmap {
        val options = BitmapFactory.Options()
        var bitmap = BitmapFactory.decodeFile(filePath, options)

        val width = bitmap.getWidth()
        val height = bitmap.getHeight()

        val rotation = getNeededRotation(filePath)

        val matrix = Matrix()
        if (rotation != 0) {
            matrix.preRotate(rotation.toFloat())
        }

        val scaleKoef = 1F / calculateInSampleSize(options, reqWidth, reqHeight)

        matrix.postScale(scaleKoef, scaleKoef)

        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true)
    }

    fun cropImage(source: Bitmap): Bitmap {
        val matrix = Matrix()
        matrix.postScale(3f, 3f)
        val width = if (source.width < source.height) source.width / 2 else source.height / 2
        val height = width
        return Bitmap.createBitmap(source, source.width / 2 - width / 2, source.height / 2 - height / 2, width, height, matrix, true)
    }

    fun updatePhotoByPath(photoPath: String): Bitmap {
        val options = BitmapFactory.Options()
        val sourceBitmap = BitmapFactory.decodeFile(File(photoPath).getAbsolutePath(), options)

        val rotation = getNeededRotation(photoPath)

        val matrix = Matrix()
        if (rotation != 0) {
            matrix.preRotate(rotation.toFloat())
        }

        return Bitmap.createBitmap(sourceBitmap, 0, 0, options.outWidth, options.outHeight, matrix, true)
    }

    fun getNeededRotation(photoPath: String): Int {
        val exif = ExifInterface(photoPath)
        var rotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        if (rotation == ExifInterface.ORIENTATION_ROTATE_90) {
            rotation = 90  }
        else if (rotation == ExifInterface.ORIENTATION_ROTATE_180) {
            rotation =  180  }
        else if (rotation == ExifInterface.ORIENTATION_ROTATE_270) {
            rotation = 270  }
        else {
            rotation = 0
        }

        return rotation
    }

}