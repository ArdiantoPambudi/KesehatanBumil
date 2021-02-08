<?php
 
require APPPATH . '/libraries/REST_Controller.php';
use Restserver\Libraries\REST_Controller;
 
class Chart extends REST_Controller {
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
            $sehat =$this->db
                    ->select('*')
                    ->from('tb_cek_kesehatan')
                    ->where('kondisi','sehat','id_moms',$id)
                    ->get()
                    ->num_rows();
            $tsehat =$this->db
                    ->select('*')
                    ->from('tb_cek_kesehatan')
                    ->where('kondisi','tidak sehat','id_moms',$id)
                    ->get()
                    ->num_rows();
        }else{
            $sehat =$this->db
                    ->select('*')
                    ->from('tb_cek_kesehatan')
                    ->where('kondisi','sehat','id_moms',$id)
                    ->get()
                    ->num_rows();
        }
        if($sehat>0 && $tsehat>0){
            $kontak = array('Tidak Sehat'=>$tsehat,'Jumlah sehat'=>$sehat);
            $this->response(array('status'=>true,'message'=>"Data Tersedia",'data'=>$kontak),200);
        }else{
            $this->response(array('status'=>false,'message'=>"Data Tidak tersedia",'data'=>$kontak),200);
        }
        

        
		// if(count($data)>0){
		// 	$this->response(array('status' => true,
		// 	'message'=>"Data Tersedia", 'data'=>$data), 200);
		// }else{
		// 	$this->response(array('status' => false,
		// 	'message'=>"Data Tidak Tersedia", 'data'=>$data), 200);
	 }
    
   
	
}