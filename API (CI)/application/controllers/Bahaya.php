<?php
 
require APPPATH . '/libraries/REST_Controller.php';
use Restserver\Libraries\REST_Controller;
 
class Bahaya extends REST_Controller {
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
				->get('tb_tandabahaya')
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
		
		$id_tandabahaya = $this->input->post('id_tandabahaya');
        $nama_bahaya = $this->input->post('nama_bahaya');
        $penyebab = $this->input->post('penyebab');
        $tindakan = $this->input->post('tindakan');
		
		
		  $data = array(
           'id_tandabahaya'    => $id_tandabahaya,
            'nama_bahaya'  => $nama_bahaya,
            'penyebab'  => $penyebab,
            'tindakan'  => $tindakan
            
        );
		
	     	if($this->validasi($data)){
			if($this->db->insert('tb_tandabahaya',$data)){
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
        $id = $this->put('id_tandabahaya');
        $data = array(
                    'id_tandabahaya'       => $this->put('id_tandabahaya'),
                    'nama_bahaya'          => $this->put('nama_bahaya'),
                    'penyebab'          => $this->put('penyebab'),
                    'tindakan'           => $this->put('tindakan'));
        $this->db->where('id_tandabahaya', $id);
        $update = $this->db->update('tb_tandabahaya', $data);
        if ($update) {
            $this->response(array('status' => true,
			'message'=>"Berhasil Terupadate"), 200);
        } else {
            $this->response(array('status' => 'fail', 502));
        }
    }
    function index_delete($param)
	{
		$this->db->where('id_tandabahaya', $param);
		if($this->db->delete('tb_tandabahaya')){
			$this->response(array('status' => true,
			'message'=>"Berhasil Terhapus"), 200);
		}else{
			$this->response(array('status' => false,
			'message'=>"Berhasil terhapus"), 200);
		}
	}
}