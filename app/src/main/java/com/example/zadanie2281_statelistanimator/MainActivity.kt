package com.example.zadanie2281_statelistanimator

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.zadanie2281_statelistanimator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAdd.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Кнопка нажата - запускаем анимацию увеличения
                    animateScale(view, 1f, 1.1f)
                    addButton() // Добавляем кнопку при нажатии
                    true // Обрабатываем касание
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    // Кнопка отпущена или касание отменено - возвращаем в исходное состояние
                    animateScale(view, 1.1f, 1f)
                    true // Обрабатываем касание
                }
                else -> false // Не обрабатываем другие события
            }
        }

        binding.buttonRemove.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Кнопка нажата - запускаем анимацию увеличения
                    animateScale(view, 1f, 1.1f) // Изменил значения для соответствия кнопке добавления
                    removeButton() // Удаляем кнопку при нажатии
                    true // Обрабатываем касание
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    // Кнопка отпущена или касание отменено - возвращаем в исходное состояние
                    animateScale(view, 1.1f, 1f)
                    true // Обрабатываем касание
                }
                else -> false // Не обрабатываем другие события
            }
        }
    }

    private fun animateScale(view: View, fromScale: Float, toScale: Float) {
        val scaleX = ObjectAnimator.ofFloat(view, "scaleX", fromScale, toScale)
        val scaleY = ObjectAnimator.ofFloat(view, "scaleY", fromScale, toScale)
        scaleX.duration = 200
        scaleY.duration = 200
        scaleX.start()
        scaleY.start()
    }

    private fun addButton() {
        val button = Button(this)
        button.text = "Button"
        binding.container.addView(button)
    }

    private fun removeButton() {
        if (binding.container.childCount > 0) {
            binding.container.removeViewAt(binding.container.childCount - 1)
        }
    }
}

