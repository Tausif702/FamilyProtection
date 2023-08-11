package com.nsztta.familyprotection

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {
    private val listContacts: ArrayList<ContactModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listMembers = listOf<MemberModel>(
            MemberModel(
                "Zishan Ali",
                "10th building , 2nd floor, magadh sugarmil , Narkatiaganj West Champaran Bihar 845455 ",
                "86",
                "42Km"
            ),
            MemberModel(
                "Taufique Ali",
                "10th building , 2nd floor, magadh sugarmil , Narkatiaganj West Champaran Bihar 845455 ",
                "45",
                "42Km"
            ),
            MemberModel(
                "Tausif Ali",
                "10th building , 2nd floor, magadh sugarmil , Narkatiaganj West Champaran Bihar 845455 ",
                "90",
                "42Km"
            ),
            MemberModel(
                "Aasif Ali",
                "10th building , 2nd floor, magadh sugarmil , Narkatiaganj West Champaran Bihar 845455 ",
                "55",
                "42Km"
            ),
            MemberModel(
                "Taufique Ali",
                "10th building , 2nd floor, magadh sugarmil , Narkatiaganj West Champaran Bihar 845455 ",
                "45",
                "42Km"
            ),
            MemberModel(
                "Tausif Ali",
                "10th building , 2nd floor, magadh sugarmil , Narkatiaganj West Champaran Bihar 845455 ",
                "90",
                "42Km"
            ),
            MemberModel(
                "Aasif Ali",
                "10th building , 2nd floor, magadh sugarmil , Narkatiaganj West Champaran Bihar 845455 ",
                "55",
                "42Km"
            )
        )
        val adapter = MemberAdopter(listMembers)
        val recycler = requireView().findViewById<RecyclerView>(R.id.recycler_member)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter
        Log.d("fetchContacts", "onViewCreated: start krne wale hai ")


        Log.d("fetchContacts", "onViewCreated: start ho gya hai ")
        val inviteAdopter = InviteAdopter(listContacts)
        Log.d("fetchContacts", "onViewCreated: end ho gya hai ")

        CoroutineScope(Dispatchers.IO).launch {
            Log.d("fetchContacts", "onViewCreated: start2 Coroutine${listContacts.size}")
            listContacts.addAll(fetchContacts())
            withContext(Dispatchers.Main) {
                inviteAdopter.notifyDataSetChanged()
            }
            Log.d("fetchContacts", "onViewCreated: end2 Coroutine ${listContacts.size}")

        }


        val inviteRecycler = requireView().findViewById<RecyclerView>(R.id.recycler_invite)
        inviteRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        inviteRecycler.adapter = inviteAdopter

    }


    private fun fetchContacts(): ArrayList<ContactModel> {
        Log.d("fetchContacts", "fetchContacts: start")

        val cr = requireActivity().contentResolver
        val cursor =
            cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        val listContacts: ArrayList<ContactModel> = ArrayList()

        if (cursor != null && cursor.count > 0) {

            while (cursor != null && cursor.moveToNext()) {
                val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val phoneNum =
                    cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))

                if (phoneNum > 0) {
                    val pCur = cr.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                        arrayOf(id),
                        ""
                    )
                    if (pCur != null && pCur.count > 0) {
                        while (pCur != null && pCur.moveToNext()) {
                            val phoneNum =
                                pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            listContacts.add(ContactModel(name, phoneNum))

                        }
                        pCur.close()
                    }
                }


            }
            if (cursor != null) {
                cursor.close()
            }
        }
        Log.d("fetchContacts", "fetchContacts: end")
        return listContacts

    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}