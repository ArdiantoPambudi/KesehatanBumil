<?php
 
require APPPATH . '/libraries/REST_Controller.php';
use Restserver\Libraries\REST_Controller;
 
class Carikeluhan extends REST_Controller {
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
 
 
    function index_get($keyword=null) {
        if ($keyword === null) {
            return 	$data = $this->db
                    ->get('tb_jeniskeluhan')->result_array();
        } else {    
            if($keyword){
                $data = $this->db
                ->or_like('nama_keluhan',$keyword)
               ->or_like('id_jeniskeluhan',$keyword)
               ->from('tb_jeniskeluhan')
               ->get()
               ->result_array();
            }
        }
        

        
		if(count($data)>0){
			$this->response(array('status' => true,
			'message'=>"Data Tersedia", 'data'=>$data), 200);
		}else{
			$this->response(array('status' => false,
			'message'=>"Data Tidak Tersedia", 'data'=>$data), 200);
		}
    }
   
	
}