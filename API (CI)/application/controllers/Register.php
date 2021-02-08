<?php
 
require APPPATH . '/libraries/REST_Controller.php';
use Restserver\Libraries\REST_Controller;
 
class Register extends REST_Controller {
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
		$data = $this->db
				->order_by('nama_lengkap','ASC')
				->from('tb_moms')
				->get()
				->result();
				
		if(count($data)>0){
			$this->response(array('status' => true,
			'message'=>"Data Tersedia", 'data'=>$data), 200);
		}else{
			$this->response(array('status' => false,
			'message'=>"Data Tidak Tersedia", 'data'=>$data), 200);
		}
    }
    function index_post() {
		//	$id_moms = $this->input->post('id_moms');
        $nama_lengkap = $this->input->post('nama_lengkap');
		$nama_suami = $this->input->post('nama_suami');
		$tinggi_badan = $this->input->post('tinggi_badan');
		$tgl_lahir = $this->input->post('tgl_lahir');
		$telepon= $this->input->post('telepon');
		$hpht= $this->input->post('hpht');
		$hpl= $this->input->post('hpl');
		$keguguran= $this->input->post('keguguran');
		$kelahiran_anak= $this->input->post('kelahiran_anak');
		$golongan_darah= $this->input->post('golongan_darah');
			  $data = array(
         //  'id_moms'    => $id_moms,
            'nama_lengkap'  => $nama_lengkap,
            'nama_suami'  => $nama_suami,
            'tinggi_badan'  => $tinggi_badan,
            'tgl_lahir' => $tgl_lahir,
			'telepon' => $telepon,
			'hpht' => $hpht,
			'hpl' => $hpl,
			'keguguran' => $keguguran,
			'kelahiran_anak' => $kelahiran_anak,
			'golongan_darah' => $golongan_darah
			// 'image' =>$nama_image
                    );
			     	if($this->validasi($data)){
			if($this->db->insert('tb_moms',$data)){
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
        $id = $this->put('id_moms');
        $data = array(
                    'id_moms'       => $this->put('id_moms'),
                    'nama_lengkap'          => $this->put('nama_lengkap'),
					'nama_suami'          => $this->put('nama_suami'),
					'tinggi_badan'          => $this->put('tinggi_badan'),
					'tgl_lahir'          => $this->put('tgl_lahir'),
					'telepon'    => $this->put('telepon'),
					'hpht'    => $this->put('hpht'),
					'hpl'    => $this->put('hpl'),
					'keguguran'    => $this->put('keguguran'),
					'kelahiran_anak'    => $this->put('kelahiran_anak'),
					'golongan_darah'    => $this->put('golongan_darah')
				);
        $this->db->where('id_moms', $id);
        $update = $this->db->update('tb_moms', $data);
        if ($update) {
            $this->response(array('status' => true,
			'message'=>"Berhasil Terupadate"), 200);
        } else {
            $this->response(array('status' => 'fail', 502));
        }
    }
    function index_delete($param)
	{
		$this->db->where('id_moms', $param);
		if($this->db->delete('tb_moms')){
			$this->response(array('status' => true,
			'message'=>"Berhasil Terhapus"), 200);
		}else{
			$this->response(array('status' => false,
			'message'=>"Berhasil terhapus"), 200);
		}
	}
}