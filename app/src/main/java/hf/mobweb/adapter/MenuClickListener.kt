package hf.mobweb.adapter

import hf.mobweb.data.Menu

interface MenuClickListener {
    fun onItemChanged(item: Menu)
    fun onItemDelete(item: Menu)
}