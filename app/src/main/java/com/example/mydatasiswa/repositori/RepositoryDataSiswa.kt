package com.example.mydatasiswa.repositori

import com.example.mydatasiswa.apiservice.ServiceApiSiswa
import com.example.mydatasiswa.modeldata.DataSiswa
import retrofit2.Response

interface RepositoryDataSiswa {
    suspend fun getDataSiswa() : List<DataSiswa>
    suspend fun postDataSiswa(dataSiswa: DataSiswa) : Response<Void>
    suspend fun getSiswaById(id: Int): DataSiswa?
    suspend fun deleteSiswa(id: Int): Response<Void>
    suspend fun updateSiswa(id: Int, dataSiswa: DataSiswa): Response<Void>
}

class JaringanRepositoryDataSiswa(
    private val serviceApiSiswa: ServiceApiSiswa
) : RepositoryDataSiswa {
    override suspend fun getDataSiswa() : List<DataSiswa> = serviceApiSiswa.getSiswa()
    override suspend fun postDataSiswa(dataSiswa: DataSiswa) : Response<Void> = serviceApiSiswa.postSiswa(dataSiswa)
    override suspend fun getSiswaById(id: Int): DataSiswa? {
        return serviceApiSiswa.getSiswaById(id).firstOrNull()
    }
    override suspend fun deleteSiswa(id: Int): Response<Void> = serviceApiSiswa.deleteSiswa(id)
    override suspend fun updateSiswa(id: Int, dataSiswa: DataSiswa): Response<Void> = serviceApiSiswa.updateSiswa(id, dataSiswa)
}