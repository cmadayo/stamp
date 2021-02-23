package com.example.stamp.ui.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.core.content.res.ResourcesCompat
import com.example.stamp.R

private const val STROKE_WIDTH = 12f

/**
 * スタンプ作成画面のお絵描き領域のビュー
 */
class DrawView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    // 2次元の描画領域
    private lateinit var extraCanvas: Canvas
    // Bitmap(描画情報のキャッシュ)
    private lateinit var extraBitmap: Bitmap

    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.colorBackground, null)
    private val drawColor = ResourcesCompat.getColor(resources, R.color.colorPaint, null)

    private lateinit var frame: Rect

    // 描画判定距離の閾値
    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop

    // モーションイベント発生座標(X座標)
    private var motionTouchEventX = 0f
    // モーションイベント発生座標(Y座標)
    private var motionTouchEventY = 0f

    // 現在位置座標(X座標)
    private var currentX = 0f
    // 現在位置座標(Y座標)
    private var currentY = 0f

    // 描画(Path)設定
    private var path = Path()
    // 描画(Paint)設定
    private val paint = Paint().apply {
        color = drawColor             // 色
        isAntiAlias = true            // アンチエイリアス
        isDither = true               // ディザリング(デバイスに合わせて色をダウンサンプリング)
        style = Paint.Style.STROKE    // 線のスタイル
        strokeJoin = Paint.Join.ROUND // 線の結合部分
        strokeCap = Paint.Cap.ROUND   // 線の先端
        strokeWidth = STROKE_WIDTH    // ストローク幅
    }

    /**
     * サイズ変更時のコールバックメソッド
     * onDrawより先によばれる
     */
    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        // メモリリーク対策で、古いBitmapをリサイクルする
        if (::extraBitmap.isInitialized) extraBitmap.recycle()

        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(backgroundColor)

        val inset = 40
        frame = Rect(inset, inset, width - inset, height - inset)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(extraBitmap, 0f, 0f, null)
        canvas.drawRect(frame, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        motionTouchEventX = event.x
        motionTouchEventY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
            MotionEvent.ACTION_UP -> touchUp()
        }
        return true
    }

    private fun touchStart() {
        path.reset()
        path.moveTo(motionTouchEventX, motionTouchEventY)
        currentX = motionTouchEventX
        currentY = motionTouchEventY
    }

    private fun touchMove() {
        // 移動距離の絶対値を算出
        val dx = Math.abs(motionTouchEventX - currentX)
        val dy = Math.abs(motionTouchEventY - currentY)

        if (dx >= touchTolerance || dy >= touchTolerance) {
            // 閾値以上移動している場合
            // QuadTo() adds a quadratic bezier from the last point,
            // approaching control point (x1,y1), and ending at (x2,y2).
            // この辺は多分、描画の補正アルゴリズムを設定している？
            path.quadTo(currentX, currentY, (motionTouchEventX + currentX) / 2, (motionTouchEventY + currentY) / 2)
            // 現在位置座標を更新
            currentX = motionTouchEventX
            currentY = motionTouchEventY
            // Draw the path in the extra bitmap to cache it.
            extraCanvas.drawPath(path, paint)
        }
        // 再描画
        invalidate()
    }

    private fun touchUp() {}

    public fun bitmap(): Bitmap {
        return extraBitmap;
    }
}