<?php
 
require APPPATH . '/libraries/REST_Controller.php';
use Restserver\Libraries\REST_Controller;
 
class Janin extends REST_Controller {
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
				->get('tb_janin')
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
		
//$id_janin = $this->input->post('id_janin');
        $minggu = $this->input->post('minggu');
		$penjelasan = $this->input->post('penjelasan');
		$nama_image = $this->input->post('gambar');
        
        $config['upload_path']          = 'upload';
        $config['allowed_types']        = 'jpg|png|jpeg';
        $config['max_size']             = '2000000';
        $config['remove_space']         = true;
        $config['overwrite']            = false;
        $config['encrypt_name']         = false;
        $config['max_width']            = '';
        $config['max_height']           = '';
		
		 $this->load->library('upload', $config);
		$this->upload->initialize($config);
		
		 if (!$this->upload->do_upload('gambar')){
            print_r('Terjadi kesalahan waktu mengupload');
            exit();
        } else {
            $image = $this->upload-> data();
            if ($image['file_name']) {
                $data['file'] = $image['file_name'];
            }
            $nama_image = $data['file'];
        }


		  $data = array(
   		//  'id_janin'    => $id_janin,
            'minggu'  => $minggu,
            'penjelasan'  => $penjelasan,
            'gambar'  => $nama_image
            
			
            
        );
		
	     	if($this->validasi($data)){
			if($this->db->insert('tb_janin',$data)){
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
	
    function index_put($id) {
		$id = $this->put('id_janin');
	    $data = array(
					'id_janin'          => $this->put('id_janin'),
                    'minggu'          => $this->put('minggu'),
					'penjelasan'          => $this->put('penjelasan'));
		// $update = $this->db->update('tb_janin', $data);
		$this->db->where('id_janin', $id);
		$update = $this->db->update('tb_janin', $data);
        if ($update) {
            $this->response(array('status' => true,
			'message'=>"Berhasil Terupadate"), 200);
        } else {
            $this->response(array('status' => 'fail', 502));
        }
	}
	
    function index_delete($param)
	{
		$this->db->where('id_janin', $param);
		if($this->db->delete('tb_janin')){
			$this->response(array('status' => true,
			'message'=>"Berhasil Terhapus"), 200);
		}else{
			$this->response(array('status' => false,
			'message'=>"Berhasil terhapus"), 200);
		}
	}

	function _uploadimages(){
		$config['upload_path']          = 'upload';
        $config['allowed_types']        = 'jpg|png|jpeg';
        $config['max_size']             = '2000000';
        $config['remove_space']         = true;
        $config['overwrite']            = false;
        $config['encrypt_name']         = false;
        $config['max_width']            = '';
        $config['max_height']           = '';
		
		 $this->load->library('upload', $config);
		$this->upload->initialize($config);
		
		 if (!$this->upload->do_upload('gambar')){
            print_r('Terjadi kesalahan waktu mengupload');
            exit();
        } else {
            $image = $this->upload-> data();
            if ($image['file_name']) {
                $data['file'] = $image['file_name'];
            }
            $nama_image = $data['file'];
        }
	}

	function ubah_gambar($id){
		

	}
}