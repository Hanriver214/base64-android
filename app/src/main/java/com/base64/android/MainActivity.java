package com.base64.android;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MainActivity extends AppCompatActivity {

    private EditText inputText;
    private EditText outputText;
    private Button encodeButton;
    private Button decodeButton;
    private Button copyButton;
    private Button clearButton;
    private Button swapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupListeners();
    }

    private void initViews() {
        inputText = findViewById(R.id.inputText);
        outputText = findViewById(R.id.outputText);
        encodeButton = findViewById(R.id.encodeButton);
        decodeButton = findViewById(R.id.decodeButton);
        copyButton = findViewById(R.id.copyButton);
        clearButton = findViewById(R.id.clearButton);
        swapButton = findViewById(R.id.swapButton);
    }

    private void setupListeners() {
        encodeButton.setOnClickListener(v -> encodeText());
        decodeButton.setOnClickListener(v -> decodeText());
        copyButton.setOnClickListener(v -> copyOutput());
        clearButton.setOnClickListener(v -> clearAll());
        swapButton.setOnClickListener(v -> swapText());
    }

    private void encodeText() {
        String input = inputText.getText().toString().trim();
        if (input.isEmpty()) {
            showToast("请输入需要编码的文本");
            return;
        }

        try {
            byte[] data = input.getBytes(StandardCharsets.UTF_8);
            String encoded = Base64.getEncoder().encodeToString(data);
            outputText.setText(encoded);
        } catch (Exception e) {
            showToast("编码失败: " + e.getMessage());
        }
    }

    private void decodeText() {
        String input = inputText.getText().toString().trim();
        if (input.isEmpty()) {
            showToast("请输入需要解码的文本");
            return;
        }

        try {
            byte[] decoded = Base64.getDecoder().decode(input);
            String output = new String(decoded, StandardCharsets.UTF_8);
            outputText.setText(output);
        } catch (IllegalArgumentException e) {
            showToast("解码失败: 无效的Base64格式");
        } catch (Exception e) {
            showToast("解码失败: " + e.getMessage());
        }
    }

    private void copyOutput() {
        String output = outputText.getText().toString().trim();
        if (output.isEmpty()) {
            showToast("没有可复制的内容");
            return;
        }

        try {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Base64结果", output);
            clipboard.setPrimaryClip(clip);
            showToast("已复制到剪贴板");
        } catch (Exception e) {
            showToast("复制失败");
        }
    }

    private void swapText() {
        String input = inputText.getText().toString();
        String output = outputText.getText().toString();
        inputText.setText(output);
        outputText.setText(input);
        inputText.setSelection(inputText.getText().length());
    }

    private void clearAll() {
        inputText.setText("");
        outputText.setText("");
        inputText.requestFocus();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}