<?php
 
require APPPATH . '/libraries/REST_Controller.php';
use Restserver\Libraries\REST_Controller;
 
class Cekkesehatan extends REST_Controller {
    private function validasi($params){
		$return = true;
		foreach ($params as $param) {
			if($param==null){
				$return = false;
			}
		}
		return $return;
	}

 
    function __construct($config = 'rest') {
        parent::__construct($config);
    }
 
	function index_get($id=null) {
		if($id == null){
		$data = $this->db
				->order_by('kode_pemeriksaan','DESC')
				->from('tb_cek_kesehatan')
				->get()
				->result();
		}else {
			$data = $this->db
				->order_by('kode_pemeriksaan','DESC')
				->where('id_moms',$id)
				->from('tb_cek_kesehatan')
				->get()
		->result();
		}
		if(count($data)>0){
			$this->response(array('status' => true,
			'message'=>"Data Tersedia", 'data'=>$data), 200);
		}else{
			$this->response(array('status' => false,
			'message'=>"Data Tidak Tersedia", 'data'=>$data), 200);
		}
    }
   
    function index_post() {
		
		//$kode_pemeriksaan = $this->input->post('kode_pemeriksaan');
        $id_moms = $this->input->post('id_moms');
		$tgl_pemeriksaan = $this->input->post('tgl_pemeriksaan');
		$keluhan = $this->input->post('keluhan');
        $berat_badan = $this->input->post('berat_badan');
        $tekanan_darah = $this->input->post('tekanan_darah');
		$tinggi_fundus = $this->input->post('tinggi_fundus');
		$denyut_jantung_janin = $this->input->post('denyut_jantung_janin');
		$lingkar_lengan_atas = $this->input->post('lingkar_lengan_atas');
		$keluhan = $this->input->post('keluhan');
		
		
		  $data = array(
           //'kode_pemeriksaan'     => $kode_pemeriksaan,
            'id_moms'        => $id_moms,
			'tgl_pemeriksaan'   => $tgl_pemeriksaan,
			'keluhan'   => $keluhan,
			'berat_badan'          => $berat_badan,
            'tekanan_darah'          => $tekanan_darah,           
            'tinggi_fundus'          => $tinggi_fundus,
            'denyut_jantung_janin'          => $denyut_jantung_janin,
			'lingkar_lengan_atas'        => $lingkar_lengan_atas,
			'keluhan'        => $keluhan
            
        );
		
	     	if($this->validasi($data)){
			if($this->db->insert('tb_cek_kesehatan',$data)){
				$this->response(array('status' => true,
				'message'=>"Berhasil ditambahkan",'data'=>$data), 200);
			}
			else{
				$this->response(array('status' => false,
				'message'=>"Gagal menyimpan"), 200);
			}
		}else{
			$this->response(array('status' => false,
				'message'=>"Parameter tidak lengkap"), 200);
		}
		
    }
	  function index_put() {
        $id = $this->put('kode_pemeriksaan');
        $data = array(
                    'kode_pemeriksaan'        => $this->put('kode_pemeriksaan'),
                    'id_moms'            => $this->put('id_moms'),
					'tgl_pemeriksaan'       => $this->put('tgl_pemeriksaan'),
					'keluhan'       => $this->put('keluhan'),
                    'berat_badan'              => $this->put('berat_badan'),
					'tekanan_darah'              => $this->put('tekanan_darah'),
					'tinggi_fundus'              => $this->put('tinggi_fundus'),
					'denyut_jantung_janin'              => $this->put('denyut_jantung_janin'),
					'lingkar_lengan_atas'            => $this->put('lingkar_lengan_atas'),
					'keluhan'            => $this->put('keluhan'));
        $this->db->where('kode_pemeriksaan', $id);
        $update = $this->db->update('tb_cek_kesehatan', $data);
        if ($update) {
            $this->response(array('status' => true,
			'message'=>"Berhasil Terupadate"), 200);
        } else {
            $this->response(array('status' => 'fail', 502));
        }
    }
    function index_delete($param)
	{
		$this->db->where('kode_pemeriksaan', $param);
		if($this->db->delete('tb_cek_kesehatan')){
			$this->response(array('status' => true,
			'message'=>"Berhasil Terhapus"), 200);
		}else{
			$this->response(array('status' => false,
			'message'=>"Berhasil terhapus"), 200);
		}
	}
}