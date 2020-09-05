package com.workteam.danil12rpl22018;

import android.net.NetworkInfo;

public class config {
    public static final String BASE_URL = "http://192.168.6.248/rentalsepeda/";

    private static final String API = "api/";
    public static final String BASE_URL_API = BASE_URL + API;

    public static final String UPLOAD_FOLDER = "uploads/";
    public static final String BASE_URL_UPLOADS = BASE_URL + UPLOAD_FOLDER;
    public static final String FIREBASE_URL = "https://dazzling-torch-6878.firebaseio.com/ABS/";

    public static final String TOAST_AN_EROR = "Mohon maaf, terjadi kendala jaringan / server";

    public static final String SHARED_PREF_NAME = "Rental Sepeda";
    public static final String LOGIN_NAME_SHARED_PREF = "NAME";
    public static final String LOGIN_ID_SHARED_PREF = "ID";
    public static final String LOGIN_ADDRESS_SHARED_PREF = "ADDRESS";
    public static final String LOGIN_CITY_SHARED_PREF = "CITY";
    public static final String LOGIN_ZIP_CODE_SHARED_PREF = "ZIP CODE";
    public static final String LOGIN_TOKEN_SHARED_PREF = "TOKEN";
    public static final String LOGIN_EMAIL_SHARED_PREF = "EMAIL";
    public static final String LOGIN_PHONE_SHARED_PREF = "PHONE";
    public static final String LOGIN_GROUP_NAME_SHARED_PREF = "GROUP";
    public static final String LOGIN_GROUP_ID_SHARED_PREF = "GROUP_ID";
    public static final String LOGIN_STATUS_SHARED_PREF = "loggedin";
    public static final String LOGIN_AVATAR_SHARED_PREF = "AVATAR";
    public static final String LOGIN_EXTRA_01_SHARED_PREF = "EXTRA_01";
    public static final String LOGIN_EXTRA_02_SHARED_PREF = "EXTRA_02";
    public static final String LOGIN_EXTRA_03_SHARED_PREF = "EXTRA_03";
    public static final String LOGIN_EXTRA_04_SHARED_PREF = "EXTRA_04";
    public static final String LOGIN_EXTRA_05_SHARED_PREF = "EXTRA_05";

    public static final int REQ_BUKTI_TF_KURANGBAYAR = 1000;
    public static final int REQ_KTP_PEMOHON = 2000;
    public static final int REQ_KTP_PASANGAN = 3000;
    public static final int REQ_KARTU_KELUARGA = 4000;
    public static final int REQ_SURAT_NIKAH = 5000;
    public static final int REQ_SLIP_GAJI = 6000;
    public static final int REQ_SK_PEGAWAI_TETAP = 7000;
    public static final int REQ_REKENING_TABUNGAN = 8000;
    public static final int REQ_NPWP = 9000;
    public static final int REQ_AKTA_PENDIRIAN = 10000;
    public static final int REQ_SIUP = 11000;
    public static final int REQ_TDP = 12000;
    public static final int REQ_SURAT_KETERANGAN = 13000;
    public static final int REQ_LAPORAN_KEUANGAN = 14000;
    public static final int REQ_SURAT_PERNYATAAN_LAIN = 15000;
    public static final int REQ_SURAT_PERNYATAAN_BELUM_MEMILIKI_RUMAH = 16000;
    public static final int REQ_SURAT_PERNYATAAN_KPR_SUBSIDI = 17000;
    public static final int REQ_SERTIFIKAT = 18000;
    public static final int REQ_IMB = 19000;
    public static final int REQ_DOKUMEN_LAIN = 20000;

    public static final String SHARED_PREF_NAME_FORM_BOOKING = "Form Booking";
    public static final String BOOK_NAMA_LENGKAP_PEMOHON = "BOOK_NAMA_PEMOHON";
    public static final String BOOK_NO_KTP_PEMOHON = "BOOK_NO_KTP_PEMOHON";
    public static final String BOOK_NAMA_GADIS_IBU_PEMOHON = "BOOK_NAMA_GADIS_IBU_PEMOHON";
    public static final String BOOK_ALAMAT_RUMAH_PEMOHON = "BOOK_ALAMAT_RUMAH_PEMOHON";
    public static final String BOOK_RT_RUMAH_PEMOHON = "BOOK_RT_RUMAH_PEMOHON";
    public static final String BOOK_RW_RUMAH_PEMOHON = "BOOK_RW_RUMAH_PEMOHON";
    public static final String BOOK_KELURAHAN_RUMAH_PEMOHON = "BOOK_KELURAHAN_RUMAH_PEMOHON";
    public static final String BOOK_KECAMATAN_RUMAH_PEMOHON = "BOOK_KECAMATAN_RUMAH_PEMOHON";
    public static final String BOOK_KOTA_RUMAH_PEMOHON = "BOOK_KOTA_RUMAH_PEMOHON";
    public static final String BOOK_KODEPOS_RUMAH_PEMOHON = "BOOK_KODEPOS_RUMAH_PEMOHON";
    public static final String BOOK_NO_TELEPON_PEMOHON = "BOOK_NO_TELEPON_PEMOHON";
    public static final String BOOK_NO_HANDPHONE1_PEMOHON = "BOOK_NO_HANDPHONE1_PEMOHON";
    public static final String BOOK_NO_HANDPHONE2_PEMOHON = "BOOK_NO_HANDPHONE2_PEMOHON";
    public static final String BOOK_JAM_FREE_PEMOHON = "BOOK_JAM_FREE_PEMOHON";
    public static final String BOOK_EMAIL_PEMOHON = "BOOK_EMAIL_PEMOHON";
    public static final String BOOK_TEMPAT_LAHIR_PEMOHON = "BOOK_TEMPAT_LAHIR_PEMOHON";
    public static final String BOOK_TANGGAL_LAHIR_PEMOHON = "BOOK_TANGGAL_LAHIR_PEMOHON";
    public static final String BOOK_PENDIDIKAN_TERAKHIR_PEMOHON = "BOOK_PENDIDIKAN_TERAKHIR_PEMOHON";
    public static final String BOOK_JENIS_PEKERJAAN_PEMOHON = "BOOK_JENIS_PEKERJAAN_PEMOHON";
    public static final String BOOK_NAMA_USAHA_PEMOHON = "BOOK_NAMA_USAHA_PEMOHON";
    public static final String BOOK_BENTUK_BADAN_USAHA_PEMOHON = "BOOK_BENTUK_BADAN_USAHA_PEMOHON";
    public static final String BOOK_BIDANG_USAHA_PEMOHON = "BOOK_BIDANG_USAHA_PEMOHON";
    public static final String BOOK_ALAMAT_USAHA_PEMOHON = "BOOK_ALAMAT_USAHA_PEMOHON";
    public static final String BOOK_KOTA_USAHA_PEMOHON = "BOOK_KOTA_USAHA_PEMOHON";
    public static final String BOOK_KODEPOS_USAHA_PEMOHON = "BOOK_KODEPOS_USAHA_PEMOHON";
    public static final String BOOK_NO_TELEPON_USAHA_PEMOHON = "BOOK_NO_TELEPON_USAHA_PEMOHON";
    public static final String BOOK_NO_TELEPON_EXT_USAHA_PEMOHON = "BOOK_NO_TELEPON_EXT_USAHA_PEMOHON";
    public static final String BOOK_NO_FAX_USAHA_PEMOHON = "BOOK_NO_FAX_USAHA_PEMOHON";
    public static final String BOOK_TAHUN_LAMA_BEKERJA_PEMOHON = "BOOK_TAHUN_LAMA_BEKERJA_PEMOHON";
    public static final String BOOK_BULAN_LAMA_BEKERJA_PEMOHON = "BOOK_BULAN_LAMA_BEKERJA_PEMOHON";
    public static final String BOOK_STATUS_PEKERJAAN_PEMOHON = "BOOK_STATUS_PEKERJAAN_PEMOHON";
    public static final String BOOK_PENDAPATAN_BULANAN_PEMOHON = "BOOK_PENDAPATAN_BULANAN_PEMOHON";

    public static final String FCM_TOKEN_REGISTRATION_COMPLETE = "FCM_TOKEN_REGISTRATION_COMPLETE";
    public static final String PUSH_NOTIFICATION = "PUSH_NOTIFICATION";

    // id to handle the notification in the notification tray
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

    public static final String SHARED_PREF_TAG_TOKEN = "SHARED_PREF_TAG_TOKEN";

    public static final String RESPONSE_STATUS_FIELD = "status";
    public static final String RESPONSE_STATUS_VALUE_SUCCESS = "success";
    public static final String RESPONSE_STATUS_VALUE_ERROR = "ERROR";
    public static final String RESPONSE_MESSAGE_FIELD = "message";
    public static final String RESPONSE_PAYLOAD_FIELD = "payload";

    public static final String ERROR_NETWORK = "Periksa kembali jaringan Anda";

    public static final int KEYWORD_SEARCH_MIN_LENGTH = 4;

    //nav item
    public static final String MENU_ADMIN_RINGKASAN = "RINGKASAN";

    public static final String MENU_ADMIN_SITEPLAN = "SITEPLAN";

    public static final String MENU_ADMIN_UNIT_SPESIFIKASI_DETAIL = "SPESIFIKASI_DETAIL";
    public static final String MENU_ADMIN_UNIT_SPESIFIKASI = "SPESIFIKASI";

    public static final String MENU_ADMIN_UNIT_IMAGE_LIST = "IMAGE_DETAIL";
    public static final String MENU_ADMIN_UNIT_IMAGE = "IMAGE";
    public static final String MENU_ADMIN_UNIT_IMAGE_NEW = "IMAGE_NEW";

    public static final String MENU_ADMIN_UNIT = "UNIT";
    public static final String MENU_ADMIN_UNIT_DETAIL = "UNIT_DETAIL";

    public static final String MENU_ADMIN_KONSUMEN = "KONSUMEN";
    public static final String MENU_ADMIN_KONSUMEN_DETAIL = "KONSUMEN_DETAIL";

    public static final String MENU_ADMIN_BOOKING = "BOOKING";
    public static final String MENU_ADMIN_BOOKING_DETAIL = "BOOKING_DETAIL";
    public static final String MENU_ADMIN_BOOKING_PEMBAYARAN = "BOOKING_PEMBAYARAN";
    public static final String MENU_ADMIN_BOOKING_PEMBAYARAN_DETAIL = "BOOKING_PEMBAYARAN_DETAIL";

    public static final String MENU_ADMIN_PESAN = "PESAN";
    public static final String MENU_ADMIN_PESAN_DETAIL = "PESAN_DETAIL";

    public static final String MENU_ADMIN_DEFAULT = "DEFAULT";

    public static final String MENU_ADMIN_KPR = "KPR";
    public static final String MENU_ADMIN_KPR_PROSES = "KPR_DETAIL";
    public static final String MENU_ADMIN_KPR_FORM = "KPR_FORM";

    //Camera request code
    //utk full size image capture
    public static final int PERMISSION_REQUEST_CAMERA = 1777;

    public static final int PERMISSION_LOCATION = 1;
    public static final int PERMISSION_CALL = 2;
    public static final int PERMISSION_WRITE_EXST = 3;
    public static final int PERMISSION_READ_EXST = 4;
    public static final int PERMISSION_CAMERA = 5;
    public static final int PERMISSION_ACCOUNTS = 6;
    public static final int PERMISSION_GPS_SETTINGS = 7;
    public static final int PERMISSION_SEND_SMS = 8;

    //File request code
    public static final int PICK_FILE_REQUEST = 1;

    public static final String RESPONSE_PAYLOAD_API_ACTION = "API_ACTION";
    public static final String RESPONSE_PAYLOAD_API_ACTION_LOGOUT = "LOGOUT";

    public static final String EVENT_BUS_RELOAD = "EB_RELOAD";

}

