<?php
 
require APPPATH . '/libraries/REST_Controller.php';
use Restserver\Libraries\REST_Controller;

class Foto extends REST_Controller {
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
		$this->load->helper('date');
		date_default_timezone_set('Asia/Jakarta');
		$this->status = true;
		$this->respon = array('status'=> $this->status, 'message'=> array());
	}

    function index_post(){
       
    if(isset($_POST['id_janin'])){
                $path = 'upload';
                $config['upload_path']          = $path;
                $config['allowed_types']        = 'jpg|png';
                $config['max_size']             = '2000000';
                $config['remove_space']			= true;
                $config['overwrite']			= false;
                $config['encrypt_name']			= false;
                $config['max_width'] 			= '';
                $config['max_height']			= '';
                $new_name = md5(time().$this->input->post('id'));
                $config['file_name'] = $new_name;
                    
                $this->load->library('upload', $config);
                $this->upload->initialize($config);
                //if path not exits
                if (!is_dir($path)){mkdir($path, 0777, true);}
                if (!$this->upload->do_upload('gambar')){
                    $this->status = false; 
                    $this->respon['message'][] = array('message'=>'Foto Kosong');
                } else {
                    $image = $this->upload->data();
                    if ($image['file_name']) {
                        $data['file'] = $image['file_name'];
                    }
                    $values['img_name'] = $data['file'];
                    // query after success save
                    //delete old file
                    $cekData = $this->db->where("id_janin",$_POST['id_janin'])->get('tb_janin');
                    if ($cekData->num_rows()>0) {
                        $namaFile = $cekData->result()[0]->gambar;
                        unlink("upload/".$namaFile);
                    }
                    //update path on table
                    $data = array("gambar"=>"".$image['file_name']);
                    $this->db->where("id_janin='".$_POST['id_janin']."'");
                    if($this->db->update('tb_janin',$data)){
                        $this->respon['message'][] = array('message'=>'Berhasil memperbarui');
                    }
                    else{
                        $this->status = false; 
                        $this->respon['message'][] = array('message'=>'Gagal memperbarui');
                    }
                }
            }else{
                $this->status = false; 
                $this->respon['message'][] = array('message'=>'Parameter tidak lengkap');
            }
            $this->respon['status'] = $this->status;
            $this->response($this->respon, 200);
    }

}