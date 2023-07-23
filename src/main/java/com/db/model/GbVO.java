package com.db.model;

import java.io.Serializable;
import java.util.Date;

public class GbVO implements Serializable {
    private Integer gb_id;
    private Integer gb_p_id;
    private Integer gb_s_price;
    private Integer gb_c_max;
    private Date gb_time_start;
    private Date gb_time_end;
    private Integer gb_status;

}
