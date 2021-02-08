<?php
 
require APPPATH . '/libraries/REST_Controller.php';
use Restserver\Libraries\REST_Controller;
 
class BidanLogin extends REST_Controller {
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
 
 
    function index_get() {
		$data = $this->db
				->get('tb_bidan')
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
        $values['nama_bidan'] = $this->input->post('nama_bidan');
        	$values['password'] = $this->input->post('password');
		// $values['password'] = md5($this->input->post('password'));
		if($this->validasi($values)){
			$cek = $this->db->where("nama_bidan", $values['nama_bidan'])
							->where("password", $values['password'])
							->get('tb_bidan')
							->result();
			if(count($cek)>0){
                $this->response(array('status' => true,
                                      'message'=>"Login berhasil", 
                                      'data'=>$cek[0]), 201);
			}
			else{
                $this->response(array('status' => false,
                    'message'=>"Username atau Password Salah".count($cek)), 202);
			}
		}
		else{
            $this->response(array('status' => false,
                'message'=>"Parameter tidak lengkap"), 200);
		}   
    }
    function index_delete($param)
	{
		$this->db->where('id_bidan', $param);
		if($this->db->delete('tb_bidan')){
			$this->response(array('status' => true,
			'message'=>"Berhasil terupdate"), 200);
		}else{
			$this->response(array('status' => false,
			'message'=>"Berhasil terupdate"), 200);
		}
	}
}