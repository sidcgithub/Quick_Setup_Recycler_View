package com.siddharthchordia.experimentalrecyclerview

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.siddharthchordia.quickrecyclerview.OnQuickItemClickListener
import com.siddharthchordia.quickrecyclerview.QuickAdapter
import com.siddharthchordia.quickrecyclerview.QuickItem
import com.siddharthchordia.quickrecyclerview.SetupQuickRecycler
import com.siddharthchordia.quickrecyclerview.SetupQuickRecycler.Companion.setupRecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var namesArray = mutableListOf<QuickItem>()


        val names = arrayOf("Sid", "Sam", "Raj")
        names.forEach { name ->
            namesArray.add(object : QuickItem {
                override var bindingMap: Map<String, Int> = mapOf(name to R.id.nameInItem,"NickName" to R.id.nickNameInItem,"https://homepages.cae.wisc.edu/~ece533/images/frymire.png" to R.id.imageInItem)
                override var layoutId: Int = R.layout.item_layout


            })
        }



        setupRecyclerView(
            this,
            testRecyclerView,
            object : OnQuickItemClickListener {
                override fun onQuickItemClick(
                    item: View?,
                    position: Int,
                    itemView: View
                ) {
                    Toast.makeText(this@MainActivity, names[position], Toast.LENGTH_LONG).show()


                }
            }
            ,
            namesArray)

        searchEditText.addTextChangedListener(object: TextWatcher
        {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                (testRecyclerView.adapter as QuickAdapter).filter(p0.toString(),R.id.nameInItem)
            }
        })


    }
}