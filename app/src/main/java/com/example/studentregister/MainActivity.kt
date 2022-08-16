package com.example.studentregister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.studentregister.db.Student
import com.example.studentregister.db.StudentDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var clearButton: Button

    private lateinit var viewModel: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.et_name)
        emailEditText = findViewById(R.id.et_email)
        saveButton = findViewById(R.id.btn_save)
        clearButton = findViewById(R.id.btn_clear)

        val dao = StudentDatabase.getInstance(application).studentDao()
        val factory = StudentViewModelFactory(dao)
        viewModel = ViewModelProvider(this,factory).get(StudentViewModel::class.java)

        saveButton.setOnClickListener{
            saveStudentData()
            clearInput()
        }

        clearButton.setOnClickListener{
            clearInput()
        }
    }

    private fun saveStudentData(){
//        val name = nameEditText.text.toString() above more beauty
//        val email = emailEditText.text.toString()
//        val student = Student(0,name, email)
//        viewModel.insertStudent(student)

        viewModel.insertStudent(
            Student(
                0,
                nameEditText.text.toString(),
                emailEditText.text.toString()
            )
        )
    }

    private fun clearInput(){
        nameEditText.setText("")
        emailEditText.setText("")
    }
}