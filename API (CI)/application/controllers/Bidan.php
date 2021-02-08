<?php
 
require APPPATH . '/libraries/REST_Controller.php';
use Restserver\Libraries\REST_Controller;
 
class Bidan extends REST_Controller {
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
		
	//	$id_bidan = $this->input->post('id_bidan');
        $nama_bidan = $this->input->post('nama_bidan');
		$password = $this->input->post('password');
		
		
		  $data = array(
        //   'id_bidan'    => $id_bidan,
            'nama_bidan'  => $nama_bidan,
            'password'  => $password
            
			
            
        );
		
	     	if($this->validasi($data)){
			if($this->db->insert('tb_bidan',$data)){
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
        $id = $this->put('id_bayi');
        $data = array(
                    'id_bidan'       => $this->put('id_bidan'),
                    'nama_bidan'          => $this->put('nama_bidan'),
                    'password'    => $this->put('password'));
        $this->db->where('id_bidan', $id);
        $update = $this->db->update('tb_bidan', $data);
        if ($update) {
            $this->response(array('status' => true,
			'message'=>"Berhasil Terupadate"), 200);
        } else {
            $this->response(array('status' => 'fail', 502));
        }
    }
    function index_delete($param)
	{
		$this->db->where('id_bidan', $param);
		if($this->db->delete('tb_bidan')){
			$this->response(array('status' => true,
			'message'=>"Berhasil Terhapus"), 200);
		}else{
			$this->response(array('status' => false,
			'message'=>"Berhasil terhapus"), 200);
		}
	}
}