package com.example.thuvienphuongnam.main.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.thuvienphuongnam.DAO.ThongKeDAO;
import com.example.thuvienphuongnam.R;
import com.example.thuvienphuongnam.databinding.FragmentDoanhthuBinding;
import com.example.thuvienphuongnam.databinding.FragmentTopBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class doanhthu extends Fragment {

private FragmentDoanhthuBinding binding;

    Button btnDoanhThu;
    ImageView btnTuNgay,btnDenNgay;
    EditText edtTuNgay,edtDenNgay;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    int mYear,mMonth,mDay;
    TextView tv;
    int temp=0;

    public View onCreateView(LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

    binding = FragmentDoanhthuBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        btnTuNgay = root.findViewById(R.id.doanhthu_img_tungay);
        btnDenNgay = root.findViewById(R.id.doanhthu_img_denngay);
        btnDoanhThu = root.findViewById(R.id.doanhthu_btn_tinh);

        edtTuNgay = root.findViewById(R.id.doanhthu_edt_tungay);
        edtDenNgay = root.findViewById(R.id.doanhthu_edt_denngay);


        tv = root.findViewById(R.id.doanhthu_tv);

        btnTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(),0,mDateTuNgay,mYear,mMonth,mDay);
                d.show();
            }
        });

        btnDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(),0,mDateDenNDen,mYear,mMonth,mDay);
                d.show();
            }
        });

        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tuNgay = edtTuNgay.getText().toString();
                String denNgay = edtDenNgay.getText().toString();
                if (tuNgay.isEmpty()||denNgay.isEmpty()){
                    Toast.makeText(getActivity(), "Không được để trống", Toast.LENGTH_SHORT).show();
                    temp++;
                }else {
                    String[] temptungay = tuNgay.split("-");
                    String[] tempdenngay = denNgay.split("-");

                    String newTungay = "";
                    String newdenngay = "";

                    int inttungay = Integer.parseInt(newTungay.concat(temptungay[0]).concat(temptungay[1]).concat(temptungay[2]));
                    int intdenngay = Integer.parseInt(newdenngay.concat(tempdenngay[0]).concat(tempdenngay[1]).concat(tempdenngay[2]));

                    if (inttungay > intdenngay) {
                        Toast.makeText(getActivity(), "Lỗi, từ ngày phải bé hơn đến ngày", Toast.LENGTH_SHORT).show();
                        temp++;
                    }
                }

                if (temp==0){
                    ThongKeDAO dao = new ThongKeDAO(getActivity());
                    tv.setText("Doanh thu: "+dao.getDoanhThu(tuNgay,denNgay)+" VNĐ");
                }else {
                    temp=0;
                }
            }
        });

    return root;
    }

    DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfYear) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfYear;
            GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
            edtTuNgay.setText(sdf.format(c.getTime()));
        }
    };

    DatePickerDialog.OnDateSetListener mDateDenNDen = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfYear) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfYear;
            GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
            edtDenNgay.setText(sdf.format(c.getTime()));
        }
    };

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}