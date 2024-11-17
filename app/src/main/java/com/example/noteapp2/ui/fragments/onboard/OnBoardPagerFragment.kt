package com.example.noteapp2.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteapp2.R
import com.example.noteapp2.databinding.FragmentOnBoardPagerBinding

class OnBoardPagerFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardPagerBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() = with(binding) {
        when (requireArguments().getInt(ARG_ONBOARD_POSITION)) {
            0 -> {
                txtTitle.text = "Удобство"
                txtBody.text =
                    "Создавайте заметки в два клика!Записывайте мысли, идеи и важные задачи мгновенно."
                animationView.setAnimation(R.raw.animationfirst)
                binding.buttonStart.visibility = View.GONE
            }

            1 -> {
                txtTitle.text = "Организация"
                txtBody.text =
                    "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время."
                animationView.setAnimation(R.raw.animationsecond)
                binding.buttonStart.visibility = View.GONE

            }

            2 -> {
                txtTitle.text = "Синхронизация"
                txtBody.text =
                    "Синхронизация на всех устройствах. Доступ к записям в любое время и в любом месте."
                animationView.setAnimation(R.raw.animationthird)
                binding.buttonStart.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        const val ARG_ONBOARD_POSITION = "onBoard"
    }
}
