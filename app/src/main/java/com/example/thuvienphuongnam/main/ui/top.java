package com.example.thuvienphuongnam.main.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.thuvienphuongnam.DAO.ThongKeDAO;
import com.example.thuvienphuongnam.DAO.ThuThuDAO;
import com.example.thuvienphuongnam.R;
import com.example.thuvienphuongnam.adapter.AdapterThuThu;
import com.example.thuvienphuongnam.adapter.AdapterTop;
import com.example.thuvienphuongnam.databinding.FragmentQlloaisachBinding;
import com.example.thuvienphuongnam.databinding.FragmentTopBinding;
import com.example.thuvienphuongnam.model.Top;

import java.util.List;


public class top extends Fragment {

private FragmentTopBinding binding;

    ListView listView;

    ThongKeDAO dao;
    List<Top> list;
    AdapterTop adapterTop;

    public View onCreateView(LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

    binding = FragmentTopBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    listView = root.findViewById(R.id.top_listview);

    loadTable();

    return root;
    }

    private void loadTable(){
        dao = new ThongKeDAO(getActivity());
        list = dao.getTop();
        adapterTop = new AdapterTop(getActivity(), R.layout.item_lv_top,list);
        listView.setAdapter(adapterTop);
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}